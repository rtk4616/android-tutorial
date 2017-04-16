package me.li2.android.tutorial.BasicUI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by weiyi on 25/03/2017.
 * https://github.com/li2
 */

public abstract class SimpleListActivity extends BasicFragmentContainerActivity {

    protected void onSimpleListItemClick(final int position) {}

    protected void setListData(List<String> titles) {
        if (mFragment != null && mFragment instanceof SimpleListFragment) {
            ((SimpleListFragment) mFragment).setListData(titles);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (mFragment != null && mFragment instanceof SimpleListFragment) {
            ((SimpleListFragment) mFragment).setOnSimpleListItemClickListener(
                    new SimpleListFragment.OnSimpleListItemClickListener() {
                        @Override
                        public void onListItemClick(int position) {
                            onSimpleListItemClick(position);
                        }
                    }
            );
        }
    }

    @Override
    protected Fragment createFragment() {
        return new SimpleListFragment();
    }


    public static class SimpleListFragment extends ListFragment {
        private final String KEY_TITLE = "title";
        private OnSimpleListItemClickListener mOnSimpleListItemClickListener;

        public interface OnSimpleListItemClickListener {
            void onListItemClick(final int position);
        }

        public void setOnSimpleListItemClickListener(SimpleListFragment.OnSimpleListItemClickListener l) {
            mOnSimpleListItemClickListener = l;
        }

        public void setListData(List<String> titles) {
            List<Map<String, String>> data = new ArrayList<>();
            for (String title : titles) {
                Map<String, String> item = new HashMap<>();
                item.put(KEY_TITLE, title);
                data.add(item);
            }

            ListAdapter listAdapter = new SimpleAdapter(getActivity(), data, android.R.layout.simple_list_item_1,
                    new String[] {KEY_TITLE}, new int[] {android.R.id.text1});
            setListAdapter(listAdapter);
        }

        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
            if (mOnSimpleListItemClickListener != null) {
                mOnSimpleListItemClickListener.onListItemClick(position);
            }
        }
    }
}
