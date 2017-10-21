package com.androidev.coding.module.commit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.androidev.coding.R;
import com.androidev.coding.model.Commit;
import com.androidev.coding.module.base.BaseActivity;
import com.androidev.coding.module.commit.adapter.CommitAdapter;
import com.androidev.coding.widget.SwipeBackLayout;
import com.androidev.refreshlayout.RefreshLayout;

public class CommitActivity extends BaseActivity {

    private RefreshLayout mRefreshLayout;
    private CommitAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SwipeBackLayout.attachTo(this);
        setContentView(R.layout.coding_activity_commit);
        CommitPresenter presenter = new CommitPresenter(this);
        mAdapter = new CommitAdapter();
        mRefreshLayout = (RefreshLayout) findViewById(R.id.coding_refresh_layout);
        mRefreshLayout.setOnRefreshListener(presenter::refresh);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.coding_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
        presenter.refresh();
    }

    void setData(Commit commit) {
        mRefreshLayout.setRefreshing(false);
        mAdapter.setData(commit);
    }

    void setError(Throwable throwable){
        mRefreshLayout.setRefreshing(false);
        throwable.printStackTrace();
    }

}
