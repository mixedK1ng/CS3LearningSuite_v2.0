/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import gologolo.GologoloApp;
import gologolo.data.GoLogoData;
import gologolo.data.GoLogoItemPrototype;
import jtps.jTPS_Transaction;

/**
 *
 * @author yiwang
 */
public class Rename_Transaction implements jTPS_Transaction{
    
    GologoloApp app;
    GoLogoData data;
    GoLogoItemPrototype prototype;
    String o;
    String n;
    public Rename_Transaction(GologoloApp initapp,GoLogoData initData,String oldname, String newname) {
        data = initData;
        app = initapp;
        o = oldname;
        n = newname;

    }
    @Override
    public void doTransaction() {
        data.getSelectedItem().setName(n);

    }

    @Override
    public void undoTransaction() {
       
        data.getSelectedItem().setName(o);

    }

    
}
