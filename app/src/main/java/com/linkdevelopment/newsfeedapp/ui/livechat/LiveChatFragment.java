package com.linkdevelopment.newsfeedapp.ui.livechat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.linkdevelopment.newsfeedapp.MainActivity;
import com.linkdevelopment.newsfeedapp.databinding.FragmentLivechatBinding;

public class LiveChatFragment extends Fragment {

    private FragmentLivechatBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentLivechatBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Toast.makeText(MainActivity.context, "Live Chat Fragment", Toast.LENGTH_SHORT).show();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}