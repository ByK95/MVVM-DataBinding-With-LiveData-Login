package com.example.umangburman.databindingwithlivedata.adapter;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class QueryAdapter {

    public Query query;

    public Query spinnerEvent(Integer position1,Integer position2,String category){
        switch (position1){
            case 0 :
                switch (position2){
                    case 0 : query = FirebaseDatabase.getInstance().getReference("items").child(category);

                        break;
                    case 1 :

                        break;
                }
                break;
            case 1 :
                switch (position2){
                    case 0 : query = FirebaseDatabase.getInstance().getReference("items").child(category);

                        break;
                    case 1 :

                        break;
                }

                break;
            case 2 :
                switch (position2){
                    case 0 : query = FirebaseDatabase.getInstance().getReference("items").child(category);

                        break;
                    case 1 :

                        break;
                }

                break;
            case 3 :
                switch (position2){
                    case 0 : query = FirebaseDatabase.getInstance().getReference("items").child(category);

                        break;
                    case 1 :

                        break;
                }

                break;
        }
        return query;
    }
}
