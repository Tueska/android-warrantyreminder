package com.example.warrantyreminder;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.warrantyreminder.databinding.FragmentProductlistBinding;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProductListFragment extends Fragment {

    private FragmentProductlistBinding binding;
    private ProductAdapter productAdapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        getActivity().setTitle("Warranty expire list");
        binding = FragmentProductlistBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView sv = (RecyclerView) getView().findViewById(R.id.productList);
        List<WarrantyEntry> products = MainActivity.sql.getAllProducts();

        products.sort((o1, o2) -> {
            return (o1.getWarrantyExpireDate() - o2.warrantyExpireDate) < 0 ? -1 : 1;
        });

        this.productAdapter = new ProductAdapter(products, this);
        sv.setAdapter(this.productAdapter);
        sv.setLayoutManager(new LinearLayoutManager(this.getContext()));

        binding.fabCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("color", getResources().getColor(R.color.cp_slate, getActivity().getTheme()));
                bundle.putLong("purchaseDate", new Date().getTime());
                bundle.putInt("warrantyLength", 2);
                bundle.putBoolean("delete", false);
                NavHostFragment.findNavController(ProductListFragment.this)
                        .navigate(R.id.action_productListFragment_to_modifyFragment, bundle);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}