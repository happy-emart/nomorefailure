package com.example.fw5_nmf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {
//    private static final int ITEM_TYPE_HEADER = 0;
//    private static final int ITEM_TYPE_ITEM = 1;
    private List<GalleryItem> galleryItemList;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public GalleryAdapter(List<GalleryItem> galleryItemList, Context c){
        this.galleryItemList = galleryItemList;
        this.context = c;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    //    Integer[] picID = {
//            R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4, R.drawable.pic5,
//            R.drawable.pic6, R.drawable.pic7, R.drawable.pic8, R.drawable.pic9, R.drawable.pic10,
//            R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4, R.drawable.pic5,
//            R.drawable.pic6, R.drawable.pic7, R.drawable.pic8, R.drawable.pic9, R.drawable.pic10,
//            R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4, R.drawable.pic5,
//            R.drawable.pic6, R.drawable.pic7, R.drawable.pic8, R.drawable.pic9, R.drawable.pic10
//    };

    // BaseAdapter를 상속받은 클래스가 구현해야 할 함수들은
    // { getCount(), getItem(), getItemId(), getView() }
    // Ctrl + i 를 눌러 한꺼번에 구현할 수 있습니다.

    @NonNull
    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_item, parent, false);
        return new GalleryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryAdapter.ViewHolder holder, int position){
        GalleryItem galleryItem = galleryItemList.get(position);
        holder.imageView.setImageResource(galleryItem.getImageResId());
    }

    @Override
    public int getItemCount() {
        return galleryItemList.size();
    }
//
//    @Override
//    public int getCount() {
//        return picID.length;
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return 0;
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            this.imageView = itemView.findViewById((R.id.imageView));

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    if (onItemClickListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            onItemClickListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        ImageView imageView = new ImageView(context);
//        imageView.setLayoutParams(new ViewGroup.LayoutParams(200,300));
//        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
//        imageView.setPadding(5,5,5,5);
//
//        imageView.setImageResource(picID[i]);
//
//
//
//        // 갤러리의 이미지뷰를 눌렀을 때
//        // 다이얼로그뷰를 팝업하여 큰 이미지를 출력합니다.
//        final int pos = i;
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                View dialogView = View.inflate(context, R.layout.dialog, null);
//                AlertDialog.Builder dlg = new AlertDialog.Builder(context);
//                ImageView ivPic = dialogView.findViewById(R.id.ivPic);
//                ivPic.setImageResource(picID[pos]);
//                dlg.setTitle("큰 이미지");
//                dlg.setIcon(R.mipmap.ic_launcher);
//                dlg.setView(dialogView);
//                dlg.setNegativeButton("닫기", null);
//                dlg.show();
//            }
//        });
//
//        return imageView;
//    }
}