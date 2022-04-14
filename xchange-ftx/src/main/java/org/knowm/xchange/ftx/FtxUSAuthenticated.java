package org.knowm.xchange.ftx;

import org.knowm.xchange.ftx.dto.FtxResponse;
import org.knowm.xchange.ftx.dto.account.*;
import org.knowm.xchange.ftx.dto.trade.CancelAllFtxOrdersParams;
import org.knowm.xchange.ftx.dto.trade.FtxModifyOrderRequestPayload;
import org.knowm.xchange.ftx.dto.trade.FtxOrderDto;
import org.knowm.xchange.ftx.dto.trade.FtxOrderRequestPayload;
import si.mazi.rescu.ParamsDigest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface FtxUSAuthenticated extends FtxAuthenticated {

  @GET
  @Path("/account")
  FtxResponse<FtxAccountDto> getAccountInformation(
      @HeaderParam("FTXUS-KEY") String apiKey,
      @HeaderParam("FTXUS-TS") Long nonce,
      @HeaderParam("FTXUS-SIGN") ParamsDigest signature,
      @HeaderParam("FTX-SUBACCOUNT") String subaccount)
      throws IOException, FtxException;

  @GET
  @Path("/wallet/balances")
  FtxResponse<List<FtxWalletBalanceDto>> getWalletBalances(
      @HeaderParam("FTXUS-KEY") String apiKey,
      @HeaderParam("FTXUS-TS") Long nonce,
      @HeaderParam("FTXUS-SIGN") ParamsDigest signature,
      @HeaderParam("FTX-SUBACCOUNT") String subaccount)
      throws IOException, FtxException;

  @GET
  @Path("/positions")
  FtxResponse<List<FtxPositionDto>> getFtxPositions(
      @HeaderParam("FTXUS-KEY") String apiKey,
      @HeaderParam("FTXUS-TS") Long nonce,
      @HeaderParam("FTXUS-SIGN") ParamsDigest signature,
      @HeaderParam("FTX-SUBACCOUNT") String subaccount)
      throws IOException, FtxException;

  @DELETE
  @Path("/subaccounts")
  FtxResponse deleteSubAccounts(
      @HeaderParam("FTXUS-KEY") String apiKey,
      @HeaderParam("FTXUS-TS") Long nonce,
      @HeaderParam("FTXUS-SIGN") ParamsDigest signature,
      FtxSubAccountRequestPOJO payload)
      throws IOException, FtxException;

  @GET
  @Path("/subaccounts")
  FtxResponse<List<FtxSubAccountDto>> getAllSubAccounts(
      @HeaderParam("FTXUS-KEY") String apiKey,
      @HeaderParam("FTXUS-TS") Long nonce,
      @HeaderParam("FTXUS-SIGN") ParamsDigest signature)
      throws IOException, FtxException;

  @POST
  @Path("/subaccounts")
  FtxResponse<FtxSubAccountDto> createSubAccount(
      @HeaderParam("FTXUS-KEY") String apiKey,
      @HeaderParam("FTXUS-TS") Long nonce,
      @HeaderParam("FTXUS-SIGN") ParamsDigest signature,
      @HeaderParam("FTX-SUBACCOUNT") String subaccount,
      FtxSubAccountRequestPOJO payload)
      throws IOException, FtxException;

  @POST
  @Path("/subaccounts/update_name")
  FtxResponse changeSubAccountName(
      @HeaderParam("FTXUS-KEY") String apiKey,
      @HeaderParam("FTXUS-TS") Long nonce,
      @HeaderParam("FTXUS-SIGN") ParamsDigest signature,
      FtxChangeSubAccountNamePOJO payload)
      throws IOException, FtxException;

  @GET
  @Path("/subaccounts/{nickname}/balances")
  FtxResponse<FtxSubAccountBalanceDto> getSubAccountBalances(
      @HeaderParam("FTXUS-KEY") String apiKey,
      @HeaderParam("FTXUS-TS") Long nonce,
      @HeaderParam("FTXUS-SIGN") ParamsDigest signature,
      @HeaderParam("FTX-SUBACCOUNT") String subaccount,
      @PathParam("nickname") String nickname)
      throws IOException, FtxException;

  @POST
  @Path("/subaccounts/transfer")
  FtxResponse<FtxSubAccountTranferDto> transferBetweenSubAccounts(
      @HeaderParam("FTXUS-KEY") String apiKey,
      @HeaderParam("FTXUS-TS") Long nonce,
      @HeaderParam("FTXUS-SIGN") ParamsDigest signature,
      @HeaderParam("FTX-SUBACCOUNT") String subaccount,
      FtxSubAccountTransferPOJO payload)
      throws IOException, FtxException;

  @POST
  @Path("/otc/quotes")
  FtxResponse<FtxConvertSimulatetDto> simulateConvert(
      @HeaderParam("FTXUS-KEY") String apiKey,
      @HeaderParam("FTXUS-TS") Long nonce,
      @HeaderParam("FTXUS-SIGN") ParamsDigest signature,
      @HeaderParam("FTX-SUBACCOUNT") String subaccount,
      FtxConvertSimulatePayloadRequestDto payload)
      throws IOException, FtxException;

  @GET
  @Path("/otc/quotes/{quoteId}")
  FtxResponse<FtxConvertDto> getConvertStatus(
      @HeaderParam("FTXUS-KEY") String apiKey,
      @HeaderParam("FTXUS-TS") Long nonce,
      @HeaderParam("FTXUS-SIGN") ParamsDigest signature,
      @HeaderParam("FTX-SUBACCOUNT") String subaccount,
      @PathParam("quoteId") String quoteId)
      throws IOException, FtxException;

  @POST
  @Path("/otc/quotes/{quoteId}/accept")
  FtxResponse<FtxConvertAcceptRequestDto> acceptConvert(
      @HeaderParam("FTXUS-KEY") String apiKey,
      @HeaderParam("FTXUS-TS") Long nonce,
      @HeaderParam("FTXUS-SIGN") ParamsDigest signature,
      @HeaderParam("FTX-SUBACCOUNT") String subaccount,
      @PathParam("quoteId") String quoteId,
      FtxConvertAcceptPayloadRequestDto payload)
      throws IOException, FtxException;

  @POST
  @Path("/orders")
  FtxResponse<FtxOrderDto> placeOrder(
      @HeaderParam("FTXUS-KEY") String apiKey,
      @HeaderParam("FTXUS-TS") Long nonce,
      @HeaderParam("FTXUS-SIGN") ParamsDigest signature,
      @HeaderParam("FTX-SUBACCOUNT") String subaccount,
      FtxOrderRequestPayload payload)
      throws IOException, FtxException;

  @POST
  @Path("/orders/{order_id}/modify")
  FtxResponse<FtxOrderDto> modifyOrder(
      @HeaderParam("FTXUS-KEY") String apiKey,
      @HeaderParam("FTXUS-TS") Long nonce,
      @HeaderParam("FTXUS-SIGN") ParamsDigest signature,
      @HeaderParam("FTX-SUBACCOUNT") String subaccount,
      @PathParam("order_id") String orderId,
      FtxModifyOrderRequestPayload payload)
      throws IOException, FtxException;

  @POST
  @Path("/orders/by_client_id/{client_order_id}/modify")
  FtxResponse<FtxOrderDto> modifyOrderByClientId(
      @HeaderParam("FTXUS-KEY") String apiKey,
      @HeaderParam("FTXUS-TS") Long nonce,
      @HeaderParam("FTXUS-SIGN") ParamsDigest signature,
      @HeaderParam("FTX-SUBACCOUNT") String subaccount,
      @PathParam("client_order_id") String clientId,
      FtxModifyOrderRequestPayload payload)
      throws IOException, FtxException;

  @GET
  @Path("/orders/{order_id}")
  FtxResponse<FtxOrderDto> getOrderStatus(
      @HeaderParam("FTXUS-KEY") String apiKey,
      @HeaderParam("FTXUS-TS") Long nonce,
      @HeaderParam("FTXUS-SIGN") ParamsDigest signature,
      @HeaderParam("FTX-SUBACCOUNT") String subaccount,
      @PathParam("order_id") String orderId)
      throws IOException, FtxException;

  @GET
  @Path("/orders?market={market}")
  FtxResponse<List<FtxOrderDto>> openOrders(
      @HeaderParam("FTXUS-KEY") String apiKey,
      @HeaderParam("FTXUS-TS") Long nonce,
      @HeaderParam("FTXUS-SIGN") ParamsDigest signature,
      @HeaderParam("FTX-SUBACCOUNT") String subaccount,
      @PathParam("market") String market)
      throws IOException, FtxException;

  @GET
  @Path("/orders")
  FtxResponse<List<FtxOrderDto>> openOrdersWithoutMarket(
      @HeaderParam("FTXUS-KEY") String apiKey,
      @HeaderParam("FTXUS-TS") Long nonce,
      @HeaderParam("FTXUS-SIGN") ParamsDigest signature,
      @HeaderParam("FTX-SUBACCOUNT") String subaccount)
      throws IOException, FtxException;

  @DELETE
  @Path("/orders/{orderId}")
  FtxResponse<String> cancelOrder(
      @HeaderParam("FTXUS-KEY") String apiKey,
      @HeaderParam("FTXUS-TS") Long nonce,
      @HeaderParam("FTXUS-SIGN") ParamsDigest signature,
      @HeaderParam("FTX-SUBACCOUNT") String subaccount,
      @PathParam("orderId") String orderId)
      throws IOException, FtxException;

  @DELETE
  @Path("/orders")
  FtxResponse<String> cancelAllOrders(
      @HeaderParam("FTXUS-KEY") String apiKey,
      @HeaderParam("FTXUS-TS") Long nonce,
      @HeaderParam("FTXUS-SIGN") ParamsDigest signature,
      @HeaderParam("FTX-SUBACCOUNT") String subaccount,
      CancelAllFtxOrdersParams payLoad)
      throws IOException, FtxException;

  @GET
  @Path("/orders/history?market={market}")
  FtxResponse<List<FtxOrderDto>> orderHistory(
      @HeaderParam("FTXUS-KEY") String apiKey,
      @HeaderParam("FTXUS-TS") Long nonce,
      @HeaderParam("FTXUS-SIGN") ParamsDigest signature,
      @HeaderParam("FTX-SUBACCOUNT") String subaccount,
      @PathParam("market") String market)
      throws IOException, FtxException;

  @DELETE
  @Path("/orders/by_client_id/{client_order_id}")
  FtxResponse<FtxOrderDto> cancelOrderByClientId(
      @HeaderParam("FTXUS-KEY") String apiKey,
      @HeaderParam("FTXUS-TS") Long nonce,
      @HeaderParam("FTXUS-SIGN") ParamsDigest signature,
      @HeaderParam("FTX-SUBACCOUNT") String subaccount,
      @PathParam("client_order_id") String clientOrderId)
      throws IOException, FtxException;

  @POST
  @Path("/account/leverage")
  FtxResponse<FtxLeverageDto> changeLeverage(
      @HeaderParam("FTXUS-KEY") String apiKey,
      @HeaderParam("FTXUS-TS") Long nonce,
      @HeaderParam("FTXUS-SIGN") ParamsDigest signature,
      @HeaderParam("FTX-SUBACCOUNT") String subaccount,
      FtxLeverageDto leverage)
      throws IOException, FtxException;

  @GET
  @Path("/spot_margin/borrow_history")
  FtxResponse<List<FtxBorrowingHistoryDto>> getBorrowHistory(
      @HeaderParam("FTXUS-KEY") String apiKey,
      @HeaderParam("FTXUS-TS") Long nonce,
      @HeaderParam("FTXUS-SIGN") ParamsDigest signature,
      @HeaderParam("FTX-SUBACCOUNT") String subaccount,
      @QueryParam("start_time") Long start_time,
      @QueryParam("end_time") Long end_time)
      throws IOException, FtxException;

  @GET
  @Path("/funding_payments")
  FtxResponse<List<FtxFundingPaymentsDto>> getFundingPayments(
      @HeaderParam("FTXUS-KEY") String apiKey,
      @HeaderParam("FTXUS-TS") Long nonce,
      @HeaderParam("FTXUS-SIGN") ParamsDigest signature,
      @HeaderParam("FTX-SUBACCOUNT") String subaccount,
      @QueryParam("start_time") Long startTime,
      @QueryParam("end_time") Long endTime,
      @QueryParam("future") String future)
      throws IOException, FtxException;

  @GET
  @Path("/spot_margin/lending_info")
  FtxResponse<List<FtxLendingInfoDto>> getLendingInfos(
      @HeaderParam("FTXUS-KEY") String apiKey,
      @HeaderParam("FTXUS-TS") Long nonce,
      @HeaderParam("FTXUS-SIGN") ParamsDigest signature,
      @HeaderParam("FTX-SUBACCOUNT") String subaccount)
      throws IOException, FtxException;

  @GET
  @Path("/spot_margin/lending_rates")
  FtxResponse<List<FtxLendingRatesDto>> getLendingRates(
      @HeaderParam("FTXUS-KEY") String apiKey,
      @HeaderParam("FTXUS-TS") Long nonce,
      @HeaderParam("FTXUS-SIGN") ParamsDigest signature)
      throws IOException, FtxException;

  @GET
  @Path("/spot_margin/lending_history")
  FtxResponse<List<FtxLendingHistoryDto>> getlendingHistories(
      @HeaderParam("FTXUS-KEY") String apiKey,
      @HeaderParam("FTXUS-TS") Long nonce,
      @HeaderParam("FTXUS-SIGN") ParamsDigest signature,
      @HeaderParam("FTX-SUBACCOUNT") String subaccount)
      throws IOException, FtxException;

  @POST
  @Path("/spot_margin/offers")
  FtxResponse submitLendingOffer(
      @HeaderParam("FTXUS-KEY") String apiKey,
      @HeaderParam("FTXUS-TS") Long nonce,
      @HeaderParam("FTXUS-SIGN") ParamsDigest signature,
      @HeaderParam("FTX-SUBACCOUNT") String subaccount,
      FtxSubmitLendingOfferParams payload)
      throws IOException, FtxException;

  @GET
  @Path("/spot_margin/borrow_rates")
  FtxResponse<List<FtxBorrowingRatesDto>> getBorrowRates(
      @HeaderParam("FTXUS-KEY") String apiKey,
      @HeaderParam("FTXUS-TS") Long nonce,
      @HeaderParam("FTXUS-SIGN") ParamsDigest signature)
      throws IOException, FtxException;

  @GET
  @Path("/spot_margin/borrow_info")
  FtxResponse<List<FtxBorrowingInfoDto>> getBorrowingInfos(
      @HeaderParam("FTXUS-KEY") String apiKey,
      @HeaderParam("FTXUS-TS") Long nonce,
      @HeaderParam("FTXUS-SIGN") ParamsDigest signature,
      @HeaderParam("FTX-SUBACCOUNT") String subaccount)
      throws IOException, FtxException;
}
