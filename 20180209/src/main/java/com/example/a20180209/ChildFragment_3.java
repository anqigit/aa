package com.example.a20180209;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * author:Created by WangZhiQiang on 2018/2/10.
 */

public class ChildFragment_3 extends Fragment {
    private ListView listView;
    public String urlString="http://api.expoon.com/AppNews/getNewsList/type/1/p/1";

    private List<Bean_.databean> list=new ArrayList<Bean_.databean>();
    private Myadapter myadapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.list_09,container,false);
        listView = view.findViewById(R.id.list_9);

        return  view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myadapter = new Myadapter(list,getActivity());
    
     new MYAS().execute(urlString);
        listView.setAdapter(myadapter);
    }

    private class MYAS extends AsyncTask<String,Void,String>{
        public MYAS() {
            super();
        }

        @Override
        protected String doInBackground(String... strings) {
            String netjson = Bean.getNetjson(strings[0]);

            return netjson;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Gson gson = new Gson();
            Bean_ bean_ = gson.fromJson(s, Bean_.class);
            List<Bean_.databean> data = bean_.getData();
            list.addAll(data);
            myadapter.notifyDataSetChanged();
        }
    }
}
