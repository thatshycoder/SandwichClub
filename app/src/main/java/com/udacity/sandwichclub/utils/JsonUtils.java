package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private final static String TAG = JsonUtils.class.getSimpleName();

    private final static String NAME = "name";
    private final static String MAIN_NAME = "mainName";
    private final static String ALSO_KNOWN_AS = "alsoKnownAs";
    private final static String PLACE_OF_ORIGIN = "placeOfOrigin";
    private final static String DESCRIPTION = "description";
    private final static String IMAGE = "image";
    private final static String INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {

        try {
            JSONObject mainJsonObject = new JSONObject(json);

            JSONObject name = mainJsonObject.getJSONObject(NAME);
            String mainName = name.getString(MAIN_NAME);

            JSONArray JSONArrayAlsoKnownAs = name.getJSONArray(ALSO_KNOWN_AS);
            List<String> alsoKnownAs = getListFromArray(JSONArrayAlsoKnownAs);

            String placeOfOrigin = mainJsonObject.getString(PLACE_OF_ORIGIN);

            String description = mainJsonObject.getString(DESCRIPTION);

            String image = mainJsonObject.getString(IMAGE);

            JSONArray JSONArrayIngredients = mainJsonObject.getJSONArray(INGREDIENTS);
            List<String> ingredients = getListFromArray(JSONArrayIngredients);

            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);

        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();

            return null;
        }
    }

    private static List<String> getListFromArray(JSONArray jsonArray) {
        List<String> list = new ArrayList<>(jsonArray.length());

        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                list.add(jsonArray.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }
}
