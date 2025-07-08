package utils;

import com.google.gson.*;
import exceptions.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JsonLocatorReader {

    private JsonObject root;

    public JsonLocatorReader() {
        try {
            root = JsonParser.parseReader(new FileReader("src/main/resources/locators/graphLocators.json"))
                    .getAsJsonObject();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundCustomException("JSON file not found at path: src/main/resources/locators/graphLocators.json", e);
        } catch (Exception e) {
            throw new JsonParseException("Failed to parse JSON config. Please check structure.", e);
        }
    }

    public List<String> getIframeLocators() {
        JsonArray array = root.getAsJsonArray("iframeLocator");
        if (array == null || array.size() == 0) {
            throw ChartValidationException.invalidLocatorTemplate("iframeLocator", "Empty or missing iframe locator array in JSON.");
        }

        List<String> locators = new ArrayList<>();
        for (JsonElement element : array) {
            locators.add(element.getAsString());
        }
        return locators;
    }

    public int getGraphIndex(String graphName) {
        JsonArray graphs = root.getAsJsonArray("Graphs");
        for (JsonElement element : graphs) {
            JsonObject graph = element.getAsJsonObject();
            if (graph.has("name") && graph.get("name").getAsString().equals(graphName)) {
                return graph.get("index").getAsInt();
            }
        }
        throw new LocatorNotFoundException("Graph index not found for: " + graphName);
    }

    public List<String> getPathLocators(String graphName) {
        JsonObject graph = getGraphObject(graphName);
        JsonObject svgSlices = graph.getAsJsonObject("svgSlices");

        if (!svgSlices.has("locators")) {
            throw ChartValidationException.invalidLocatorTemplate("svgSlices", "Missing locators array.");
        }

        JsonArray locators = svgSlices.getAsJsonArray("locators");
        List<String> list = new ArrayList<>();
        for (JsonElement el : locators) {
            list.add(el.getAsString());
        }
        return list;
    }

    public List<String> getPercentageLocators(String graphName) {
        JsonObject graph = getGraphObject(graphName);

        if (!graph.has("percentageTextLocators")) {
            throw ChartValidationException.percentageNotFound(graphName, "Key 'percentageTextLocators' missing.");
        }

        JsonArray locators = graph.getAsJsonArray("percentageTextLocators");
        if (locators.size() == 0) {
            throw ChartValidationException.percentageNotFound(graphName, "Locator array is empty.");
        }

        List<String> list = new ArrayList<>();
        for (JsonElement el : locators) {
            list.add(el.getAsString());
        }

        return list;
    }

    private JsonObject getGraphObject(String graphName) {
        JsonArray graphs = root.getAsJsonArray("Graphs");
        for (JsonElement element : graphs) {
            JsonObject graph = element.getAsJsonObject();
            if (graph.has("name") && graph.get("name").getAsString().equals(graphName)) {
                return graph;
            }
        }
        throw new LocatorNotFoundException("Graph configuration not found for: " + graphName);
    }
}
