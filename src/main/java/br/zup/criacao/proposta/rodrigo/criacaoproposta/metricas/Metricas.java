package br.zup.criacao.proposta.rodrigo.criacaoproposta.metricas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Component
public class Metricas {

	private final MeterRegistry registroMetrica;

	private Collection<String> strings = new ArrayList<>();

	public Metricas(MeterRegistry registroMetrica) {
		this.registroMetrica = registroMetrica;
		criarGauge();
	}

	public void addContador(String key) {
		Counter contaPropostasCriadas = this.registroMetrica.counter(key);
		contaPropostasCriadas.increment();

	}

	private void criarGauge() {
		this.registroMetrica.gauge("meu_gauge", strings, Collection::size);

	}

	@Scheduled(fixedDelay = 10000)
	public void simulaGauge() {
		Random random = new Random();
		double randomNumber = random.nextInt();

		if (randomNumber % 2 == 0) {
			addString();

		} else {
			removeString();
		}
	}

	public void removeString() {
		strings.removeIf(Objects::nonNull);
	}

	public void addString() {
		strings.add(UUID.randomUUID().toString());
	}

}