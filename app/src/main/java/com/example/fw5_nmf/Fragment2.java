package com.example.fw5_nmf;

import android.graphics.Rect;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;


public class Fragment2 extends Fragment {
    private RecyclerView galleryRecyclerView;
    private GalleryAdapter galleryAdapter;

    public Fragment2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_2, container, false);

        galleryRecyclerView = view.findViewById(R.id.galleryRecyclerView);
        galleryRecyclerView.setHasFixedSize(true);

        int spanCount = 3;
//        int rowCount = 4;
        int spacing = 50;
        boolean includeEdge = false;

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), spanCount);
//        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){
//            @Override
//            public int getSpanSize(int position){
//                int viewType = galleryAdapter.getItemViewType(position);
//                if (viewType == ITEM_TYPE_HEADER) {
//                    return spanCount;
//                } else {
//                    return spanCount / rowCount;
//                }
//            }
//        });
        galleryRecyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        galleryRecyclerView.setLayoutManager(layoutManager);

        List<GalleryItem> galleryItemList = createGalleryItemList();

        galleryAdapter = new GalleryAdapter(galleryItemList, getActivity());
        galleryAdapter.setOnItemClickListener(new GalleryAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(int position) {
                View dialogView = View.inflate(getActivity(), R.layout.dialog, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity());
                ImageView ivPic = dialogView.findViewById(R.id.ivPic);
                ivPic.setImageResource(galleryItemList.get(position).getImageResId());
                dlg.setTitle("큰 이미지");
                dlg.setIcon(R.mipmap.ic_launcher);
                dlg.setView(dialogView);
                dlg.setNegativeButton("닫기", null);
                dlg.show();
            }
        });
        galleryRecyclerView.setAdapter(galleryAdapter);

//        final GridView gv = view.findViewById(R.id.gallery);
//        GalleryAdapter gAdapter = new GalleryAdapter(getActivity());

//        gv.setAdapter(gAdapter);

        return view;
    }

    private List<GalleryItem> createGalleryItemList() {
        List<GalleryItem> galleryItemList = new ArrayList<>();

        int[] imageResIds = {
                R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4, R.drawable.pic5,
                R.drawable.pic6, R.drawable.pic7, R.drawable.pic8, R.drawable.pic9, R.drawable.pic10,
                R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4, R.drawable.pic5,
                R.drawable.pic6, R.drawable.pic7, R.drawable.pic8, R.drawable.pic9, R.drawable.pic10,
                R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4, R.drawable.pic5,
                R.drawable.pic6, R.drawable.pic7, R.drawable.pic8, R.drawable.pic9, R.drawable.pic10
        };

        for (int resId:imageResIds) {
            GalleryItem galleryItem = new GalleryItem(resId, 0);
            galleryItemList.add(galleryItem);
        }
        return galleryItemList;
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }
}