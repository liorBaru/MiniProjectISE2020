package domain;

import java.util.TreeMap;

public class Season
{
    private int year;
    private boolean start;
    private TreeMap<League, SeasonInfo> teams;

    public Season(int year, boolean start) {
        this.year = year;
        this.start = start;
        teams=new TreeMap<>();
    }
}
