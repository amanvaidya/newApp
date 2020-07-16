package com.audit_new.scanner.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.audit_new.scanner.R;
import com.audit_new.scanner.adapter.ProductListAdapter;
import com.audit_new.scanner.helper.ProductCatalogueList;
import com.audit_new.scanner.service.DAO.ProductDAO;
import com.audit_new.scanner.service.pojo.ProductDetails;
import com.audit_new.scanner.service.remote.ApiUtils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductSelectionFragment extends Fragment {
    private RecyclerView mRecyclerView;
    String req_id;
    private ProductListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    Button button;
    ProductDAO productDAO;
    String authKey;
    SharedPreferences preferences;
    ArrayList<ProductDetails> productCatalogueList;
    int prdQty=0;
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_product_selection, container, false);
        Bundle bundle = this.getArguments();
        productDAO = ApiUtils.getAllProduct();
        req_id = bundle.getString("req_id");
        preferences = this.getActivity().getSharedPreferences("user_details", Context.MODE_PRIVATE);
        authKey=preferences.getString("auth_token",null);
        createProductList();
        mRecyclerView = v.findViewById(R.id.recyclerView);
        button = (Button) v.findViewById(R.id.btnCheckout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new PRNumberGeneration();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return v;
    }

    public void createProductList() {
        Call<ArrayList<ProductDetails>> call = productDAO.getMyProducts(authKey);
        call.enqueue(new Callback<ArrayList<ProductDetails>>() {
            @Override
            public void onResponse(Call<ArrayList<ProductDetails>> call, Response<ArrayList<ProductDetails>> response) {
                productCatalogueList=response.body();
                buildRecyclerView(productCatalogueList);
            }

            @Override
            public void onFailure(Call<ArrayList<ProductDetails>> call, Throwable t) {

            }
        });
    }
    public void buildRecyclerView(final ArrayList<ProductDetails> productCatalogueList){
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new ProductListAdapter(productCatalogueList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new ProductListAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                if((productCatalogueList.get(position).getmCount())>0) {
                    productCatalogueList.get(position).changeCount(productCatalogueList.get(position).getmCount()-1);
                    mAdapter.notifyItemChanged(position);
                }
            }
            @Override
            public void onAddClick(int position) {
               productCatalogueList.get(position).changeCount(productCatalogueList.get(position).getmCount()+1);
                mAdapter.notifyItemChanged(position);
            }
        });
    }
}
