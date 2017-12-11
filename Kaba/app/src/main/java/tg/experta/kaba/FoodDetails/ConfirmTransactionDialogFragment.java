package tg.experta.kaba.FoodDetails;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;

import tg.experta.kaba.R;

/**
 * By abiguime on 2017/12/6.
 * email: 2597434002@qq.com
 */

public class ConfirmTransactionDialogFragment extends DialogFragment {



    Listener mListener;


    // TODO: Customize parameters
    public static ConfirmTransactionDialogFragment newInstance(int itemCount) {
        final ConfirmTransactionDialogFragment fragment = new ConfirmTransactionDialogFragment();
        final Bundle args = new Bundle();
//        args.putInt(ARG_ITEM_COUNT, itemCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {

        super.onResume();

        int width = getResources().getDisplayMetrics().widthPixels;
        getDialog().getWindow().setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT);
    }


    ImageButton ib_confirm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.confirm_transaction_dialog_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ib_confirm = view.findViewById(R.id.ib_confirm);

        ib_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        final Fragment parent = getParentFragment();
        if (parent != null) {
            mListener = (ConfirmTransactionDialogFragment.Listener) parent;
        } else {
            mListener = (ConfirmTransactionDialogFragment.Listener) context;
        }
    }

    @Override
    public void onDetach() {
        mListener = null;
        super.onDetach();
    }

    public interface Listener {

    }


}
