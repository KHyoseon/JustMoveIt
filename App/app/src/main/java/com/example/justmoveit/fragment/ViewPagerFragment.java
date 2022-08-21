package com.example.justmoveit.fragment;

import static com.example.justmoveit.activity.LoadingActivity.movieSP;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.justmoveit.R;
import com.example.justmoveit.activity.MainActivity;
import com.example.justmoveit.adapters.MoviesAdapter;
import com.example.justmoveit.model.Movie;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ViewPagerFragment extends Fragment {
    private int rankBy;  // general_ranking(0) or my_ranking(1)
    private MainActivity activity;
    private ViewGroup rootView;

    public ViewPagerFragment(int rankBy){
        this.rankBy = rankBy;
    }

    // 화면이 붙을 때 작동
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // 현재 소속된 액티비티를 메인 액티비티로 한다.
        activity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //fragment_xml를 MainFragment.java와 묶어주는 역할을 하는 메서드
        //inflate 시키면 Object 타입으로 넘어오기 때문에 캐스팅 필요
        //(사용할 자원, 자원을 담을 곳, T/F)
        //메인에 직접 들어가면 True, 프래그먼트에 있으면 False
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_view_page, container, false);
        setupMoviesViewPager();

        return rootView;
    }

    private void setupMoviesViewPager() {
        // 메인 페이지 - 영화 목록 (카드)
        ViewPager2 moviesViewPager = rootView.findViewById(R.id.moviesViewPager);
        moviesViewPager.setClipToPadding(false);
        moviesViewPager.setClipChildren(false);
        moviesViewPager.setOffscreenPageLimit(3);
        moviesViewPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(10));
        compositePageTransformer.addTransformer(((page, position) -> {
            float r = 1 - Math.abs(position);
            page.setScaleY(0.85f + r * 0.15f);
        }));
        moviesViewPager.setPageTransformer(compositePageTransformer);

        // SP에 저장된 무비 리스트 가져와서 어댑트
        Gson gson = new Gson();
        List<Movie> movies = new ArrayList<>();

        if(rankBy == 1) {
            movies = gson.fromJson(movieSP.getString("my_ranking", ""), TypeToken.getParameterized(ArrayList.class, Movie.class).getType());
        } else {
            Map<String, ?> map = movieSP.getAll();
            Set<String> keys = map.keySet();
            for(String key: keys){
                if(key.equals("my_ranking"))    continue;
                movies.add(gson.fromJson(movieSP.getString(key, ""), Movie.class));
            }
        }
        // 없으면 종료
        if(movies == null || movies.size() == 0){
            Log.e("setupMoviesViewPager", "there are no movies in SP");
            return;
        }

        moviesViewPager.setAdapter(new MoviesAdapter(movies));
    }

}
