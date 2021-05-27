package com.example.warrantyreminder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.warrantyreminder.databinding.FragmentProductlistBinding;

import java.util.Date;
import java.util.List;

public class ProductListFragment extends Fragment {

    private FragmentProductlistBinding binding;
    private ProductAdapter productAdapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        getActivity().setTitle("warranty expire list");
        binding = FragmentProductlistBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView warrantyEntryNotifier = view.findViewById(R.id.warrantyEntryNotifier);
        RecyclerView sv = (RecyclerView) getView().findViewById(R.id.productList);
        List<WarrantyEntry> products = MainActivity.sql.getAllProducts();
        if(products.isEmpty()) {
            warrantyEntryNotifier.setText("no warranty entries");
        }

        // Lambda Expression → Sorts Arraylist by remaining time
        products.sort((o1, o2) -> (o1.getWarrantyExpireDate() - o2.getWarrantyExpireDate()) < 0 ? -1 : 1);

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