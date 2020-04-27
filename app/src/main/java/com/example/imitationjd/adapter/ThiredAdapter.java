package com.example.imitationjd.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.imitationjd.R;
import com.example.imitationjd.activity.ThiredActivity;
import com.example.imitationjd.fragment.BottomFragment;
import com.example.imitationjd.model.ShopModel;
import com.example.imitationjd.viewmodel.MyViewModel;
import com.example.imitationjd.weiget.MyThiredBottomLinLerLayout;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import static com.example.imitationjd.activity.ThiredActivity.THIRED_TYPE_0;
import static com.example.imitationjd.activity.ThiredActivity.THIRED_TYPE_1;

public class ThiredAdapter extends RecyclerView.Adapter {
   private List<ShopModel> shopModelList;
   private ThiredActivity activity;

    public ThiredAdapter(List<ShopModel> shopModelList, ThiredActivity activity) {
        this.shopModelList = shopModelList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == THIRED_TYPE_0){
            return new HeaserViewHolder(activity.getLayoutInflater().inflate(R.layout.item_thired_header,parent,false));
        }else if (viewType ==THIRED_TYPE_1){
            return new ViewPagerViewHolder(activity.getLayoutInflater().inflate(R.layout.item_thired_bottom,parent,false),this);
        }else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaserViewHolder){
            HeaserViewHolder heaserViewHolder = (HeaserViewHolder) holder;
            if (position % 2 == 0){
                heaserViewHolder.textView.setBackgroundColor(activity.getResources().getColor(R.color.colorAccent));
            }else {
                heaserViewHolder.textView.setBackgroundColor(activity.getResources().getColor(R.color.colorPrimary));
            }
            heaserViewHolder.textView.setText(shopModelList.get(position).getTextString());
        }else if (holder instanceof ViewPagerViewHolder){
            ViewPagerViewHolder viewPagerViewHolder = (ViewPagerViewHolder) holder;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return shopModelList.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return shopModelList.size();
    }

    static class HeaserViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public HeaserViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_item_thired_header_title);
        }
    }


   public static class ViewPagerViewHolder extends RecyclerView.ViewHolder implements ViewPager.OnPageChangeListener {
        List<BottomFragment> fragmentList = new ArrayList<>();
        WeakReference<ThiredAdapter> thiredAdapterWeakReference;
        MyThiredBottomLinLerLayout myThiredBottomLinLerLayout;
        ViewPager viewPager;
        MyViewModel myViewModel;
        int currentIndex = 0;
        public ViewPagerViewHolder(@NonNull final View itemView, ThiredAdapter thiredAdapter) {
            super(itemView);
            thiredAdapterWeakReference = new WeakReference<ThiredAdapter>(thiredAdapter);

            myViewModel = new ViewModelProvider(thiredAdapterWeakReference.get().activity.getViewModelStore(), ViewModelProvider.AndroidViewModelFactory.getInstance(thiredAdapterWeakReference.get().activity.getApplication())).get(MyViewModel.class);
            myViewModel.getIntegerMutableLiveData().observe(thiredAdapterWeakReference.get().activity, new Observer<Integer>() {
                @Override
                public void onChanged(Integer integer) {
                    RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) itemView.getLayoutParams();
                    layoutParams.height = integer;
                    itemView.setLayoutParams(layoutParams);
                }
            });

            viewPager = itemView.findViewById(R.id.thired_viewpager);
            myThiredBottomLinLerLayout = itemView.findViewById(R.id.item_thired_bottom_mythired_linlerlayout);

            myViewModel.getMyThiredLinLerLayoutMutableLiveData().setValue(myThiredBottomLinLerLayout);

            for (int i = 0; i < 4; i++) {
                fragmentList.add(new BottomFragment(i));
            }
            viewPager.setAdapter(new FragmentAdapter(thiredAdapterWeakReference.get().activity.getSupportFragmentManager(),fragmentList));
            viewPager.addOnPageChangeListener(this);
            viewPager.setOffscreenPageLimit(4);
            myThiredBottomLinLerLayout.setThiredActivity(thiredAdapterWeakReference.get().activity);


        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            currentIndex = position;
            if (fragmentList.get(currentIndex).getRecyclerView()!=null){
                myViewModel.getSelected().setValue(fragmentList.get(currentIndex).getRecyclerView());
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
