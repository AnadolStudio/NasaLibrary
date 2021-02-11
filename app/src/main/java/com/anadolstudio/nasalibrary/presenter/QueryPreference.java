package com.anadolstudio.nasalibrary.presenter;

import android.content.Context;
import android.preference.PreferenceManager;

import static com.anadolstudio.nasalibrary.presenter.MainPresenter.DEFAULT_QUERY;
import static com.anadolstudio.nasalibrary.presenter.MainPresenter.START_QUERY_PAGE;

public class QueryPreference {

    private static final String SEARCH_QUERY = "search_query";
    private static final String QUERY_PAGE = "query_page";

    public static String getSearchQuery(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(SEARCH_QUERY, DEFAULT_QUERY);
    }

    public static void setSearchQuery(Context context, String query) {
        // Если запрос меняется, то страница обнуляется (=1)
        setQueryPage(context, START_QUERY_PAGE);

        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(SEARCH_QUERY, query)
                .apply();
    }

    public static int getQueryPage(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getInt(QUERY_PAGE, START_QUERY_PAGE);
    }

    public static void setQueryPage(Context context, int page) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putInt(QUERY_PAGE, page)
                .apply();
    }

}
