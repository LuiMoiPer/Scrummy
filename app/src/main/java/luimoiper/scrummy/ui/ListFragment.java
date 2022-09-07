package luimoiper.scrummy.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import luimoiper.scrummy.R;

public class ListFragment extends Fragment {
    private RecyclerView.Adapter adapter;
    private View.OnClickListener fabListener;

    public ListFragment(@NonNull RecyclerView.Adapter adapter, View.OnClickListener fabListener) {
        super();
        this.adapter = adapter;
        this.fabListener = fabListener;
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        view.findViewById(R.id.fab).setOnClickListener(fabListener);
        return view;
    }
}
