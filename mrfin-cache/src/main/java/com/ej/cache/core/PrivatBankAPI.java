package com.ej.cache.core;

import com.ej.cache.core.interfaces.BankAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;


/**
 * {@link PrivatBankAPI} is the class to communicate with the Privatbank of Ukraine and to get an actual currency
 * exchange rates.
 *
 * Implements the {@link BankAPI} interface.
 *
 */
@Component
public class PrivatBankAPI implements BankAPI {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrivatBankAPI.class);

    @Override
    public String getResponse() {
        try {
            Properties properties = new Properties();
            properties.load(getClass().getClassLoader().getResourceAsStream("cache.properties"));
            String currencyJsonUrl = properties.getProperty("PRIVATBANK_JSON_URL");
            StringBuilder builder = new StringBuilder();
            URL url = new URL(currencyJsonUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream stream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String out;
            while ((out = reader.readLine()) != null) {
                builder.append(out);
            }
            return builder.toString();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }
}
