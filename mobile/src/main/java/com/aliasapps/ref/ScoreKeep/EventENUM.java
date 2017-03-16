package com.aliasapps.ref.ScoreKeep;

import com.aliasapps.ref.R;

/**
 * Created by mike on 2017-03-16.
 */

public class EventENUM {

    public static int GOAL = 0;
    public static int YELLOW = 1;
    public static int RED = 2;
    public static int HALF = 3;
    public static int OTHER = 4;

    public static int GOAL_RESOURCE = R.drawable.abc_ab_share_pack_mtrl_alpha;
    public static int YELLOW_RESOURCE = R.drawable.abc_ab_share_pack_mtrl_alpha;
    public static int RED_RESOURCE = R.drawable.abc_ab_share_pack_mtrl_alpha;
    public static int HALF_RESOURCE = R.drawable.abc_ab_share_pack_mtrl_alpha;
    public static int OTHER_RESOURCE = R.drawable.abc_ab_share_pack_mtrl_alpha;

    public static int getEventResource(Event event){
        switch (event.getType()){
            case 0: return GOAL_RESOURCE;
            case 1: return YELLOW_RESOURCE;
            case 2: return RED_RESOURCE;
            case 3: return HALF_RESOURCE;
            default: return OTHER_RESOURCE;
        }
    }
}
