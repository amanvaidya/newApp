package com.audit_new.scanner.adapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.audit_new.scanner.R;
import com.audit_new.scanner.fragments.PRNumberGeneration;
import com.audit_new.scanner.helper.ProductCatalogueList;
import com.audit_new.scanner.service.pojo.ProductDetails;

import java.util.ArrayList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {

    private ArrayList<ProductDetails> mProductList;
    private OnItemClickListener mListener;


    public interface OnItemClickListener {

        void onDeleteClick(int position);
        void onAddClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i) {
        ProductDetails currentItem = mProductList.get(i);

        //productViewHolder.mImageView.setImageResource(currentItem.getImageResource());
        productViewHolder.mTextView1.setText(currentItem.getGroupName());
        productViewHolder.mTextView2.setText(currentItem.getProduct_name());
        productViewHolder.productCount.setText(String.valueOf(currentItem.getmCount()));

        Fragment fragment = new PRNumberGeneration();
        Bundle bundle     = new Bundle();
        bundle.putInt("prod_id",currentItem.getProd_id());
        bundle.putString("prod_name",currentItem.getProduct_name());
        bundle.putString("prod_desc",currentItem.getProduct_desc());
        bundle.putString("asset_type",currentItem.getAsset_type());
        bundle.putString("model",currentItem.getModel());
        bundle.putString("manufacturer",currentItem.getManufacturer());
        bundle.putString("groupName",currentItem.getGroupName());
        bundle.putString("uom",currentItem.getUOM());
        bundle.putString("group_Id",currentItem.getGroup_Id());
        bundle.putInt("qty",currentItem.getmCount());
        fragment.setArguments(bundle);
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;
        public ImageView mDelImage;
        public ImageView mAddImage;
        int prdCount = 0;
        public TextView productCount;
        public ProductViewHolder(View itemView, final OnItemClickListener listener){
            super(itemView);
            mImageView = itemView.findViewById(R.id.PrProdImg);
            mTextView1 = itemView.findViewById(R.id.PrProdTxt1);
            mTextView2 = itemView.findViewById(R.id.PrProdTxt2);
            mDelImage  = itemView.findViewById(R.id.image_delete);
            mAddImage  = itemView.findViewById(R.id.image_add);
            productCount=itemView.findViewById(R.id.prCount);

            mDelImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
            mAddImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onAddClick(position);
                        }
                    }
                }
            });
        }

    }

    /*Passing Array List*/
    public ProductListAdapter(ArrayList<ProductDetails> productCatalogueList) {
        mProductList = productCatalogueList;
    }
    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_catalogue_list, parent, false);
        ProductViewHolder pvh = new ProductViewHolder(v, mListener);
        return pvh;
    }


}
