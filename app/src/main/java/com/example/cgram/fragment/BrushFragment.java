package com.example.cgram.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cgram.R;
import com.example.cgram.adapter.ColorAdapter;
import com.example.cgram.utils.BrushFragmentListener;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class BrushFragment extends BottomSheetDialogFragment implements ColorAdapter.ColorAdapterListener {

    private static BrushFragment instance;
    private BrushFragmentListener listener;

    public void setListener(BrushFragmentListener listener) {
        this.listener = listener;
    }

    public static BrushFragment getInstance() {
        if (instance == null) {
            instance = new BrushFragment();
        }
        return instance;
    }

    public BrushFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_brush, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SeekBar sbOpacity = view.findViewById(R.id.sb_opacity);
        SeekBar sbSize = view.findViewById(R.id.sb_size);
        ToggleButton btnState = view.findViewById(R.id.btn_brush_state);
        RecyclerView rvColor = view.findViewById(R.id.rv_colors);
        rvColor.setHasFixedSize(true);
        rvColor.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        ColorAdapter adapter = new ColorAdapter(getContext(), this);
        rvColor.setAdapter(adapter);

        sbOpacity.setOnSeekBarChangeListener(opacityListener);
        sbSize.setOnSeekBarChangeListener(sizeListener);
        btnState.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                listener.onBrushStateChangedListener(b);
            }
        });
    }

    private SeekBar.OnSeekBarChangeListener opacityListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            listener.onBrushOpacityChangedListener(i);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private SeekBar.OnSeekBarChangeListener sizeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            listener.onBrushSizeChangedListener(i);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            
        }
    };

    @Override
    public void onColorSelected(int color) {
        listener.onBrushColorChangedListener(color);
        dismiss();
    }
}
