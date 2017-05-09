/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb138.db.entity;

/**
 *
 * @author xjanco1
 */
public class Goal {
    private int minute;
    private String scorer;
    private String firstAssist;
    private String secondAssist;

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getScorer() {
        return scorer;
    }

    public void setScorer(String scorer) {
        this.scorer = scorer;
    }

    public String getFirstAssist() {
        return firstAssist;
    }

    public void setFirstAssist(String firstAssist) {
        this.firstAssist = firstAssist;
    }

    public String getSecondAssist() {
        return secondAssist;
    }

    public void setSecondAssist(String secondAssist) {
        this.secondAssist = secondAssist;
    }
}
