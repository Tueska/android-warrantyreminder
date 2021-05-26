package com.example.warrantyreminder;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.warrantyreminder.databinding.FragmentFirstBinding;

import java.util.Date;
import java.util.List;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private ProductAdapter productAdapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView sv = (RecyclerView) getView().findViewById(R.id.productList);
        List<WarrantyEntry> products = MainActivity.sql.getAllProducts();

        this.productAdapter = new ProductAdapter(products, this);
        sv.setAdapter(this.productAdapter);
        sv.setLayoutManager(new LinearLayoutManager(this.getContext()));

        binding.fabCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("color", R.color.cp_slate);
                bundle.putLong("purchaseDate", new Date().getTime());
                bundle.putInt("warrantyLength", 2);
                bundle.putBoolean("delete", false);
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_modifyFragment, bundle);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}