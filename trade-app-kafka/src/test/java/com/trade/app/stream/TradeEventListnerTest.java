
package com.trade.app.stream;

import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.TestPropertySource;
import static org.mockito.ArgumentMatchers.argThat;

@EmbeddedKafka(brokerProperties = "log.dir=target/${random.uuid}/embedded-kafka")
@TestPropertySource(properties = {
		// bridge between embedded Kafka and Spring Cloud Stream
		"spring.cloud.stream.kafka.binder.brokers=${spring.embedded.kafka.brokers}",
		// using real kafka
		"spring.autoconfigure.exclude=org.springframework.cloud.stream.test.binder.TestSupportBinderAutoConfiguration",
		"spring.cloud.stream.bindings.messageRequestInput.group=consumer",
		"spring.cloud.stream.bindings.messageRequestInput.destination=messages",
		"spring.cloud.stream.bindings.messageRequestOutput.destination=messages" })
public class TradeEventListnerTest {
	@Autowired
	private TradeEventListener listener;

	@TestConfiguration
	static class Config {

		@Bean
		public BeanPostProcessor messageRequestListenerPostProcessor() {
			return new ProxiedMockPostProcessor(TradeEventListener.class);
		}

		static class ProxiedMockPostProcessor implements BeanPostProcessor {
			private final Class<?> mockedClass;

			public ProxiedMockPostProcessor(Class<?> mockedClass) {
				this.mockedClass = mockedClass;
			}

			@Override
			public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
				if (mockedClass.isInstance(bean)) {
					return Mockito.mock(mockedClass, AdditionalAnswers.delegatesTo(bean));
				}
				return bean;
			}
		}
	}

	@Test
	public void messageIsReceived() {
		listener.publishTrade(null);

		// the message actually gets received. Need to do a timeout because I cannot
		// manually force
		// a consumption of this message from Kafka. The default for timeout() is to
		// check every
		// 10ms up to the timeout
		verify(listener, timeout(5000)).handleTradeEvent("S");
	}

}