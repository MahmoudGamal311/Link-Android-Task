package com.linkdevelopment.newsfeedapp.ui.livechat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.linkdevelopment.newsfeedapp.databinding.FragmentLivechatBinding;

public class LiveChatFragment extends Fragment {

    private FragmentLivechatBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        LiveChatViewModel slideshowViewModel =
                new ViewModelProvider(this).get(LiveChatViewModel.class);

        binding = FragmentLivechatBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSlideshow;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}