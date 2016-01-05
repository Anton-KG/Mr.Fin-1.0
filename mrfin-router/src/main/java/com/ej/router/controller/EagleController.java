package com.ej.router.controller;

import com.ej.api.CurrencyPair;
import com.ej.router.domain.Order;
import com.ej.router.exceptions.CacheResponseException;
import com.ej.router.services.ConvertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * Class EagleController is implements the controller mission in router  module.
 */
@RestController
@RequestMapping(value = "/convert", method = RequestMethod.GET)
public class EagleController {
    @Autowired
    private ConvertService convertService;
    private static final int BROWSER_NAME_END_INDEX = 32;
    private final Logger logger = LoggerFactory.getLogger(EagleController.class);

    /**
     * @param baseCurrency  {@code String}  base currency.
     * @param quoteCurrency {@code String}  currency in which  the {@link ConvertService} instance should convert.
     * @param amount        {@code String} representation  amount of currency which is need to be converted.
     * @param request       {@code HttpServletRequest} request
     * @return the {@code EagleResponse} object as json representation.
     * This method is mapped on {@code url}  "/convert".
     */
    @RequestMapping(value = "/{baseCurrency}/{quoteCurrency}/{amount}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<EagleResponse> convert(@PathVariable String baseCurrency,
                                                 @PathVariable String quoteCurrency,
                                                 @PathVariable String amount, HttpServletRequest request) {

        EagleResponse response = new EagleResponse();
        try {
            if (verifyAmount(amount)) {
                try {

                    String browserName = request.getHeader("user-agent");
                    if (browserName == null || browserName.isEmpty()) {
                        browserName = "Unknown";
                    } else {
                        browserName = browserName.substring(0, BROWSER_NAME_END_INDEX);
                    }

                    Order orderToConvert = getOrder(new CurrencyPair(baseCurrency, quoteCurrency),
                            browserName, amount);
                    BigDecimal converted = convertService.convert(orderToConvert);
                    response.setMessage("OK");
                    response.setConvertedAmount(converted.toString());
                    response.setStatus(HttpStatus.OK.toString());
                    return new ResponseEntity(response, HttpStatus.OK);
                } catch (CacheResponseException e) {
                    logger.error(e.getMessage(), e);
                    response.setMessage(e.getMessage());
                    response.setConvertedAmount("");
                    response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
                    return new ResponseEntity(response, HttpStatus.OK);
                } catch (Exception e) {
                    logger.error("Service error", e);
                    response.setMessage("ConvertServiceError");
                    response.setConvertedAmount("");
                    response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
                    return new ResponseEntity(response, HttpStatus.OK);
                }
            }
            response.setStatus(HttpStatus.BAD_REQUEST.toString());
            response.setConvertedAmount("");
            response.setMessage("Invalid arguments");
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus(HttpStatus.BAD_REQUEST.toString());
            response.setMessage("Invalid arguments in request");
            response.setConvertedAmount("");
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    private Order getOrder(CurrencyPair currencyPair, String browser, String amount) {
        Order order = new Order();
        order.setCurrencyPair(currencyPair);
        order.setBrowserName(browser);
        order.setAmount(new BigDecimal(Integer.parseInt(amount)));

        return order;
    }

    private boolean verifyAmount(String amount) throws NumberFormatException {
        return new BigDecimal(amount).doubleValue() > 0;
    }
}
