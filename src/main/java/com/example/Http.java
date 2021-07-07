package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.UnknownHostException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Fetches the response body and headers for the Star-Wars-API HTTP GET request
 */
public class Http {
    private URL urlquery;

    /**
     * Constructs an object of Http
     *
     * @param url the complete url for the GET request
     * @throws MalformedURLException
     */
    public Http(String url) throws MalformedURLException {
        try {
            this.urlquery = new URL(url);
        }

        catch (MalformedURLException e) {
            throw e;
        }
    }

    /**
     * Get the response body without response headers
     *
     * @return the reponse
     */
    public String fullResponse() {
        return this.fullResponse(false);
    }

    /**
     * Get the response body and/or response headers
     *
     * @param header <code>true</code> for including header in the response
     * @return the response
     */
    public String fullResponse(boolean header) {
        String response = "";

        try {
            HttpURLConnection conn = (HttpURLConnection) this.urlquery.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/json");

            response = this.getResponse(conn, header);

            // writing the response to a file - for testing
            ToFile.write("out.json", response, false);
        }

        // thrown by setRequestMethod
        catch (ProtocolException e) {
            System.err.println("Protocol is not supported");
        }

        catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return response;
    }

    /**
     * Creates the response from a HttpURLConnection connection
     *
     * @param conn HttpURLConnection
     * @return response body
     */
    private String getResponse(HttpURLConnection conn, boolean header) {
        StringBuilder sb = new StringBuilder();

        // Reader to hold the response body inputStream or errorStream
        Reader streamReader = null;

        try {
            // step 1: check the response codes
            // --------------------------------------------------------------------------------
            int responseCode = conn.getResponseCode();
            // --------------------------------------------------------------------------------

            // step 2: get the response headers
            // --------------------------------------------------------------------------------
            if (header == true) {
                // get a Map of response header fields
                Map<String, List<String>> responseHeaders = conn.getHeaderFields();

                // get a Set of all keys in the Map
                Set<String> headers = responseHeaders.keySet();

                // iterating over every keys in the Set
                for (String key : headers) {
                    // the key is null in exactly one case
                    if (key != null) {
                        sb.append(key + " = ");
                    }

                    // get the value of every key
                    List<String> headerValues = responseHeaders.get(key);

                    // get an iterator over the key
                    Iterator<String> itr = headerValues.iterator();

                    while (itr.hasNext()) {
                        sb.append(itr.next());

                        if (itr.hasNext()) {
                            sb.append(",");
                        }
                    }

                    sb.append(String.format("%n"));
                }

                // for extra line of separation between response header and body
                sb.append(String.format("%n"));
            }
            // --------------------------------------------------------------------------------

            // step 4: get the response body
            // --------------------------------------------------------------------------------
            // get the appropriate stream using the response status code
            if (responseCode > 299) {
                streamReader = new InputStreamReader(conn.getErrorStream());
            }

            else {
                streamReader = new InputStreamReader(conn.getInputStream());
            }

            // now reading the response body - whether InputStream or ErrorStream
            BufferedReader br = new BufferedReader(streamReader);
            String currentLine;

            // read till null is not encountered
            while ((currentLine = br.readLine()) != null) {
                sb.append(currentLine);
            }

            br.close();
            // --------------------------------------------------------------------------------
        }

        // thrown when host doesn't exist or is down
        catch (UnknownHostException e) {
            System.err.printf("Can't connect. Could not resolve host.");
        }

        // thrown when a host uses invalid security certificate
        catch (javax.net.ssl.SSLHandshakeException e) {
            System.err.printf("Potential security issue. Could not find valid certification path to requested target.");
        }

        catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
