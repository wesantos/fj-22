package br.com.caelum.argentum.modelo;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;

public class NegociacaoTest {

	@Test
	public void dataNegociacaoEhImutavel() {
		//se criar u negocio no dia 15
		
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 15);
		
		Negociacao n = new Negociacao(10, 5, c);
		
		//ainda que tente mudar data para dia 20
		n.getData().set(Calendar.DAY_OF_MONTH, 20);
		
		// ele continua no dia 15
		Assert.assertEquals(15, n.getData().get(Calendar.DAY_OF_MONTH));
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoCriaNegociacaoComDataNula() {
		new Negociacao(10, 5, null);
	}
	
	@Test
	public void mesmoMilissegundoEhDoMesmoDia() {
		Calendar agora = Calendar.getInstance();
		Calendar mesmoMomento = (Calendar) agora.clone();
		
		Negociacao negociacao = new Negociacao(40.0, 100, agora);
		Assert.assertTrue(negociacao.isMesmoDia(mesmoMomento));
	}
	@Test
	public void comHorariosDiferentesEhNoMesmoDia() {
		//usando GregorianCalendar (ano, mes, dia, hora, minuto)
		Calendar manha = new GregorianCalendar(2011, 10, 20, 8, 30);
		Calendar tarde = new GregorianCalendar(2011, 10, 20, 15, 30);
		
		Negociacao negociacao = new Negociacao(40.0, 100, manha);
		Assert.assertTrue(negociacao.isMesmoDia(tarde));
	}
	
	@Test
	public void mesmoDiaMasMesesDiferentesNaoSaoDoMesmoDia() {
		
		Calendar janeiro = new GregorianCalendar(2011, 1, 20, 8, 30);
		Calendar marco = new GregorianCalendar(2011, 3, 20, 8, 30);

		Negociacao negociacao = new Negociacao(30.0, 100, janeiro);
		Assert.assertFalse(negociacao.isMesmoDia(marco));
	}
	
	@Test
	public void mesmoDiaEMesMasAnosDiferentesNaoSaoDoMesmoDia() {
		
		Calendar anoAtual = new GregorianCalendar(2017, 3, 29, 22, 25);
		Calendar anoPassado = new GregorianCalendar(2016, 3, 29, 22, 25);

		Negociacao negociacao = new Negociacao(20.0, 100, anoAtual);
		Assert.assertFalse(negociacao.isMesmoDia(anoPassado));
	}
	
}
