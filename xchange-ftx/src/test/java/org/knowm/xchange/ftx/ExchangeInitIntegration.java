package org.knowm.xchange.ftx;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.Test;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;

import java.io.IOException;

public class ExchangeInitIntegration {

  @Test
  public void ftxInitializationTest() {
    Exchange ftx = ExchangeFactory.INSTANCE.createExchange(FtxExchange.class);

    assertThat(ftx.getExchangeSymbols().isEmpty()).isFalse();
    assertThat(ftx.getExchangeInstruments().isEmpty()).isFalse();
  }

  @Test
  public void ftxInitializationTest2() throws IOException {
    ExchangeSpecification exchangeSpecification = new FtxExchange().getDefaultExchangeSpecification();
    exchangeSpecification.setSslUri("https://ftx.us");
    exchangeSpecification.setHost("ftx.us");
    exchangeSpecification.setApiKey("PXehrBU4JpmYF6MblA8R2K9eYmydC8Es3Oog0yr7");
    exchangeSpecification.setSecretKey("bwrdl5du8nPxeLhz6rpmI5ciKjijKjTU94A1-r_F");

    var exchange = ExchangeFactory.INSTANCE.createExchange(exchangeSpecification);

    assertThat(exchange.getExchangeSymbols().isEmpty()).isFalse();
    assertThat(exchange.getExchangeInstruments().isEmpty()).isFalse();

    var accountInfo = exchange.getAccountService().getAccountInfo();
    System.out.println("account info " + accountInfo.toString());
  }
}
