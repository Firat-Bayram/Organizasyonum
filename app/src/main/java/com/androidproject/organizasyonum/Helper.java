package com.androidproject.organizasyonum;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Firat on 1.1.2015.
 * last update 29.01.2015
 */
public final class Helper {
    public final static Helper INSTANCE = new Helper();

    /**
     * Herbir log yazma işleminin bir tipi vardır
     */
    public enum logTypes {
        information,
        error,
        debug
    }

    private Helper() {
    }

    /**
     * Ekranı kapamanızı sağlar.
     */
    public void cikis(Activity activity) {
        activity.finish();
    }

    /**
     * Activity üstündeki View den türemiş olan kontrollerinize erişmenizi sağlamaktadır.
     *
     * @param activity
     * @param id
     * @param type
     * @param <T>
     * @return bununan kontrolü geri dönmektedir.
     */
    public <T extends View> T getirView(Activity activity, int id, Class<T> type) {
        return type.cast(activity.findViewById(id));
    }


    /**
     * dialog üstündeki View den türemiş olan kontrollerinize erişmenizi sağlamaktadır.
     *
     * @param dialog
     * @param id
     * @param type
     * @param <T>
     * @return bununan kontrolü geri dönmektedir.
     */
    public <T extends View> T getirView(Dialog dialog, int id, Class<T> type) {
        return type.cast(dialog.findViewById(id));
    }

    private void EkleParametre(Intent intent, Map params) throws Exception {
        for (Object key : params.keySet()) {
            Object value = params.get(String.valueOf(key));

            if (value.getClass() == String.class)
                intent.putExtra(String.valueOf(key), (String) (params.get(String.valueOf(key))));
            else if (value.getClass() == String[].class)
                intent.putExtra(String.valueOf(key), (String[]) (params.get(String.valueOf(key))));
            else if (value.getClass() == Integer.class)
                intent.putExtra(String.valueOf(key), (int) (params.get(String.valueOf(key))));
            else if (value.getClass() == Integer[].class)
                intent.putExtra(String.valueOf(key), (int[]) (params.get(String.valueOf(key))));
            else if (value.getClass() == Long.class)
                intent.putExtra(String.valueOf(key), (long) (params.get(String.valueOf(key))));
            else if (value.getClass() == Long[].class)
                intent.putExtra(String.valueOf(key), (long[]) (params.get(String.valueOf(key))));
            else if (value.getClass() == Date.class)
                intent.putExtra(String.valueOf(key), (Date) (params.get(String.valueOf(key))));
            else if (value.getClass() == Date[].class)
                intent.putExtra(String.valueOf(key), (Date[]) (params.get(String.valueOf(key))));
            else if (value.getClass() == Float.class)
                intent.putExtra(String.valueOf(key), (float) (params.get(String.valueOf(key))));
            else if (value.getClass() == Float[].class)
                intent.putExtra(String.valueOf(key), (float[]) (params.get(String.valueOf(key))));
            else if (value.getClass() == Short.class)
                intent.putExtra(String.valueOf(key), (short) (params.get(String.valueOf(key))));
            else if (value.getClass() == Short[].class)
                intent.putExtra(String.valueOf(key), (short[]) (params.get(String.valueOf(key))));
            else if (value.getClass() == Byte.class)
                intent.putExtra(String.valueOf(key), (byte) (params.get(String.valueOf(key))));
            else if (value.getClass() == Byte[].class)
                intent.putExtra(String.valueOf(key), (byte[]) (params.get(String.valueOf(key))));
            else if (value.getClass() == CharSequence.class)
                intent.putExtra(String.valueOf(key), (CharSequence) (params.get(String.valueOf(key))));
            else if (value.getClass() == CharSequence[].class)
                intent.putExtra(String.valueOf(key), (CharSequence[]) (params.get(String.valueOf(key))));
            else if (value.getClass() == Boolean.class)
                intent.putExtra(String.valueOf(key), (boolean) (params.get(String.valueOf(key))));
            else if (value.getClass() == Boolean[].class)
                intent.putExtra(String.valueOf(key), (boolean[]) (params.get(String.valueOf(key))));
            else if (value.getClass() == char.class)
                intent.putExtra(String.valueOf(key), (char) (params.get(String.valueOf(key))));
            else if (value.getClass() == char[].class)
                intent.putExtra(String.valueOf(key), (char[]) (params.get(String.valueOf(key))));
            else if (value.getClass() == Double.class)
                intent.putExtra(String.valueOf(key), (double) (params.get(String.valueOf(key))));
            else if (value.getClass() == Double[].class)
                intent.putExtra(String.valueOf(key), (double[]) (params.get(String.valueOf(key))));
            else if (value.getClass() == Parcelable.class)
                intent.putExtra(String.valueOf(key), (Parcelable) (params.get(String.valueOf(key))));
            else if (value.getClass() == Parcelable[].class)
                intent.putExtra(String.valueOf(key), (Parcelable[]) (params.get(String.valueOf(key))));
            else if (value.getClass() == Parcelable.class)
                intent.putExtra(String.valueOf(key), (Serializable) (params.get(String.valueOf(key))));
            else if (value.getClass() == Parcelable[].class)
                intent.putExtra(String.valueOf(key), (Serializable[]) (params.get(String.valueOf(key))));
            else if (value.getClass() == Bundle.class)
                intent.putExtra(String.valueOf(key), (Bundle) (params.get(String.valueOf(key))));
            else
                throw new Exception(value.getClass().toString() + " tipi geçersiz bir tiptir.");
        }
    }

    /**
     * @param packageContext
     * @param cls
     */
    public void goster(Context packageContext, Class<?> cls) {
        Intent intent = new Intent(packageContext, cls);
        packageContext.startActivity(intent);
    }

    /**
     * Örnek Kullanım
     * Map<String,String> userMap = new HashMap<String, String>()
     * Map vehicles = new HashMap();
     * vehicles.put("BMW", 5);
     *
     * @param packageContext
     * @param cls
     * @param params
     * @throws Exception
     */
    public void goster(Context packageContext, Class<?> cls, Map params) throws Exception {
        Intent intent = new Intent(packageContext, cls);
        EkleParametre(intent, params);
        packageContext.startActivity(intent);
    }

    /**
     * @param activity
     * @param cls
     */
    public void gosterDialog(Activity activity, Class<?> cls) {
        Intent intent = new Intent(activity, cls);
        activity.startActivityForResult(intent, 1);
    }

    /**
     * @param activity
     * @param cls
     * @param params
     * @throws Exception
     */
    public void gosterDialog(Activity activity, Class<?> cls, Map params) throws Exception {
        Intent intent = new Intent(activity, cls);
        EkleParametre(intent, params);
        activity.startActivityForResult(intent, 1);
    }

    /**
     * @param activity
     * @param params
     * @param resultCode
     * @throws Exception
     */
    public void sonuclaDialog(Activity activity, Map params, int resultCode) throws Exception {
        Intent intent = activity.getIntent();
        EkleParametre(intent, params);
        activity.setResult(resultCode, intent);
    }

    /**
     * @param activity
     * @return
     */
    public Bundle getirEkranParametrelerini(Activity activity) {
        return activity.getIntent().getExtras();
    }

    /**
     * Paralel olaran çalışmasını isteriğimiz işlemler içindir.
     *
     * @param mc Runnable interface inden türeyen Run methoduna sahip olan sınıf
     */
    public <T extends Runnable> void calistirParalel(T mc) {
        Thread thread = new Thread(mc);
        thread.start();
    }

    /**
     * Zaman ayarlı olaran belli aralıklarda tetiklenmesi gerekli olan görevler içindir.
     *
     * @param task          Runnable interface inden türeyen Run methoduna sahip olan TimerTask nesnesi
     * @param baslamaSuresi Tetiklendikten ne kadar milisaniye sonra çalışmaya başlamalı
     * @param periyotSuresi milisaniye aralıklar ile tetiklenmeli
     */
    public void calistirZamanli(TimerTask task, long baslamaSuresi, long periyotSuresi) {
        Timer timer = new Timer();
        timer.schedule(task, baslamaSuresi, periyotSuresi);
    }

    /**
     * Ekranın altında belli bir süre gösterdikten sonra kendiliğinden kapanan bilgi mesajları için kullanılır
     */
    public void gosterBilgiMesaji(Activity activity, CharSequence mesaj) {
        Toast.makeText(activity.getApplicationContext(), mesaj, Toast.LENGTH_LONG).show();
    }

    /**
     * Log tipine göre loglama yapmaktadır.
     */
    public void yazLog(logTypes logTipi, String tag, String mesaj) {
        switch (logTipi) {
            case information:
                if (String.valueOf(tag).isEmpty())
                    tag = "info";
                Log.i(tag, mesaj);
                break;
            case error:
                if (String.valueOf(tag).isEmpty())
                    tag = "error";
                Log.e(tag, mesaj);
                break;
            case debug:
                if (String.valueOf(tag).isEmpty())
                    tag = "debug";
                Log.d(tag, mesaj);
                break;
            default:
                break;
        }
    }

    /**
     * Log tipine göre loglama yapmaktadır.
     */
    public void yazLog(logTypes logTipi, String mesaj) {
        switch (logTipi) {
            case information:
                yazLog(logTipi, "info", mesaj);
                break;
            case error:
                yazLog(logTipi, "error", mesaj);
                break;
            case debug:
                yazLog(logTipi, "debug", mesaj);
                break;
            default:
                break;
        }
    }

    /**
     * SMS gönderir.
     *
     * @param telefonNumarasi SMS gönderilecek telefon numarası
     * @param smsIcerigi      Gönderilecek SMS in içeriği
     */
    public void gonderSMS(String telefonNumarasi, String smsIcerigi) {
        android.telephony.SmsManager sms = android.telephony.SmsManager.getDefault();
        sms.sendTextMessage(telefonNumarasi, null, smsIcerigi, null, null);
    }

    private AlertDialog.Builder GetirAlertBuilder(Activity activity, int alertDialogThemeId) {
        AlertDialog.Builder builder;
        if (alertDialogThemeId > 0)
            builder = new AlertDialog.Builder(activity, alertDialogThemeId);
        else
            builder = new AlertDialog.Builder(activity);

        return builder;
    }

    private Dialog GetirDialog(Activity activity, int alertDialogThemeId) {
        Dialog dialog;
        if (alertDialogThemeId > 0)
            dialog = new Dialog(activity, alertDialogThemeId);
        else
            dialog = new Dialog(activity);

        return dialog;
    }

    /**
     * @param activity
     * @param alertDialogThemeId
     * @param baslik
     * @param icerik
     * @param isCancelable
     * @param yesListener
     * @param noListener
     * @return
     */
    public AlertDialog showMessageBoxYesNo(Activity activity, int alertDialogThemeId, String baslik, String icerik, boolean isCancelable,
                                           DialogInterface.OnClickListener yesListener,
                                           DialogInterface.OnClickListener noListener) {
        AlertDialog.Builder builder = GetirAlertBuilder(activity, alertDialogThemeId);
        builder.setTitle(baslik);
        builder.setCancelable(isCancelable);
        builder.setMessage(icerik);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setPositiveButton("Evet", yesListener);
        builder.setNegativeButton("Hayır", noListener);

        return builder.create();
    }

    /**
     * @param activity
     * @param alertDialogThemeId
     * @param baslik
     * @param icerik
     * @param isCancelable
     * @param yesListener
     * @param noListener
     * @param cancelListener
     * @return
     */
    public AlertDialog showMessageBoxYesNoCancel(Activity activity, int alertDialogThemeId, String baslik, String icerik, boolean isCancelable,
                                                 DialogInterface.OnClickListener yesListener,
                                                 DialogInterface.OnClickListener noListener,
                                                 DialogInterface.OnClickListener cancelListener) {
        AlertDialog.Builder builder = GetirAlertBuilder(activity, alertDialogThemeId);
        builder.setTitle(baslik);
        builder.setCancelable(isCancelable);
        builder.setMessage(icerik);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setPositiveButton("Evet", yesListener);
        builder.setNegativeButton("Hayır", noListener);
        builder.setNeutralButton("İptal", cancelListener);

        return builder.create();
    }

    /**
     * @param activity
     * @param alertDialogThemeId
     * @param baslik
     * @param isCancelable
     * @param chsSize
     * @param singleChoiceItemsListener
     * @param okListener
     * @param cancelListener
     * @return
     */
    public AlertDialog showMessageBoxRadioList(Activity activity, int alertDialogThemeId, String baslik, boolean isCancelable, CharSequence[] chsSize,
                                               DialogInterface.OnClickListener singleChoiceItemsListener,
                                               DialogInterface.OnClickListener okListener,
                                               DialogInterface.OnClickListener cancelListener) {
        AlertDialog.Builder builder = GetirAlertBuilder(activity, alertDialogThemeId);
        builder.setTitle(baslik);
        builder.setCancelable(isCancelable);
        builder.setSingleChoiceItems(chsSize, 0 /*sel.item*/, singleChoiceItemsListener);
        builder.setPositiveButton("Tamam", okListener);
        builder.setNeutralButton("İptal", cancelListener);

        return builder.create();
    }

    /**
     * @param activity
     * @param alertDialogThemeId
     * @param baslik
     * @param isCancelable
     * @param itemsId
     * @param singleChoiceItemsListener
     * @param okListener
     * @param cancelListener
     * @return
     */
    public AlertDialog showMessageBoxRadioList(Activity activity, int alertDialogThemeId, String baslik, boolean isCancelable, int itemsId,
                                               DialogInterface.OnClickListener singleChoiceItemsListener,
                                               DialogInterface.OnClickListener okListener,
                                               DialogInterface.OnClickListener cancelListener) {
        AlertDialog.Builder builder = GetirAlertBuilder(activity, alertDialogThemeId);
        builder.setTitle(baslik);
        builder.setCancelable(isCancelable);
        builder.setSingleChoiceItems(itemsId, 0 /*sel.item*/, singleChoiceItemsListener);
        builder.setPositiveButton("Tamam", okListener);
        builder.setNeutralButton("İptal", cancelListener);

        return builder.create();
    }

    /**
     * @param activity
     * @param alertDialogThemeId
     * @param baslik
     * @param isCancelable
     * @param items
     * @param checkedItems
     * @param multiChoiceItemsListener
     * @param okListener
     * @param cancelListener
     * @return
     */
    public AlertDialog showMessageBoxCheckList(Activity activity, int alertDialogThemeId, String baslik, boolean isCancelable, CharSequence[] items, boolean[] checkedItems,
                                               DialogInterface.OnMultiChoiceClickListener multiChoiceItemsListener,
                                               DialogInterface.OnClickListener okListener,
                                               DialogInterface.OnClickListener cancelListener) {
        AlertDialog.Builder builder = GetirAlertBuilder(activity, alertDialogThemeId);
        builder.setTitle(baslik);
        builder.setCancelable(isCancelable);
        builder.setMultiChoiceItems(items, checkedItems, multiChoiceItemsListener);
        builder.setPositiveButton("Tamam", okListener);
        builder.setNeutralButton("İptal", cancelListener);

        return builder.create();
    }

    /**
     * @param activity
     * @param alertDialogThemeId
     * @param baslik
     * @param isCancelable
     * @param itemsId
     * @param checkedItems
     * @param multiChoiceItemsListener
     * @param okListener
     * @param cancelListener
     * @return
     */
    public AlertDialog showMessageBoxCheckList(Activity activity, int alertDialogThemeId, String baslik, boolean isCancelable, int itemsId, boolean[] checkedItems,
                                               DialogInterface.OnMultiChoiceClickListener multiChoiceItemsListener,
                                               DialogInterface.OnClickListener okListener,
                                               DialogInterface.OnClickListener cancelListener) {
        AlertDialog.Builder builder = GetirAlertBuilder(activity, alertDialogThemeId);
        builder.setTitle(baslik);
        builder.setCancelable(isCancelable);
        builder.setMultiChoiceItems(itemsId, checkedItems, multiChoiceItemsListener);
        builder.setPositiveButton("Tamam", okListener);
        builder.setNeutralButton("İptal", cancelListener);

        return builder.create();
    }

    /**
     * @param activity
     * @param alertDialogThemeId
     * @param baslik
     * @param icerik
     */
    public void showMessage(Activity activity, int alertDialogThemeId, String baslik, String icerik) {
        AlertDialog alertMessage = GetirAlertBuilder(activity, alertDialogThemeId).create();
        alertMessage.setTitle(baslik);
        alertMessage.setMessage(icerik);
        alertMessage.show();
    }

    /**
     * @param activity
     * @param alertDialogThemeId
     * @param baslik
     * @param isCancelable
     * @param items
     * @param itemListener
     * @return
     */
    public AlertDialog showMessageBoxList(Activity activity, int alertDialogThemeId, String baslik, boolean isCancelable, CharSequence[] items,
                                          DialogInterface.OnClickListener itemListener) {
        AlertDialog.Builder builder = GetirAlertBuilder(activity, alertDialogThemeId);
        builder.setTitle(baslik);
        builder.setCancelable(isCancelable);
        builder.setItems(items, itemListener);

        return builder.create();
    }

    /**
     * @param activity
     * @param alertDialogThemeId
     * @param baslik
     * @param isCancelable
     * @param itemsId
     * @param itemListener
     * @return
     */
    public AlertDialog showMessageBoxList(Activity activity, int alertDialogThemeId, String baslik, boolean isCancelable, int itemsId,
                                          DialogInterface.OnClickListener itemListener) {
        AlertDialog.Builder builder = GetirAlertBuilder(activity, alertDialogThemeId);
        builder.setTitle(baslik);
        builder.setCancelable(isCancelable);
        builder.setItems(itemsId, itemListener);

        return builder.create();
    }

    /**
     * @param activity
     * @param alertDialogThemeId
     * @param baslik
     * @param isCancelable
     * @return
     */
    public ProgressDialog showProgressDialogBox(Activity activity, int alertDialogThemeId, String baslik, boolean isCancelable) {
        ProgressDialog mProgressDialog = new ProgressDialog(activity, alertDialogThemeId);
        mProgressDialog.setIconAttribute(android.R.attr.alertDialogIcon);
        mProgressDialog.setTitle(baslik);
        mProgressDialog.setCancelable(isCancelable);
        mProgressDialog.setMax(100);
        mProgressDialog.setProgress(0);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
      /*  mProgressDialog.setButton(DialogInterface.BUTTON_POSITIVE,
        "pozitif", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                *//* User clicked Yes so do some stuff *//*
                mProgressDialog.incrementProgressBy(1);
                dialog.dismiss();
            }
        });
        mProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE,
        "negatif", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                *//* User clicked No so do some stuff *//*
               int progress= mProgressDialog.getProgress();
                mProgressDialog.setProgress(--progress);
                dialog.dismiss();
            }
        });*/

        return mProgressDialog;
    }

    /**
     * @param activity
     * @param baslik
     * @param isCancelable
     * @return
     */
    public ProgressDialog showProgressDialogBox(Activity activity, String baslik, boolean isCancelable) {
        ProgressDialog mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setIconAttribute(android.R.attr.alertDialogIcon);
        mProgressDialog.setTitle(baslik);
        mProgressDialog.setCancelable(isCancelable);
        mProgressDialog.setMax(100);
        mProgressDialog.setProgress(0);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
      /*  mProgressDialog.setButton(DialogInterface.BUTTON_POSITIVE,
        "pozitif", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                *//* User clicked Yes so do some stuff *//*
                mProgressDialog.incrementProgressBy(1);
                dialog.dismiss();
            }
        });
        mProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE,
        "negatif", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                *//* User clicked No so do some stuff *//*
               int progress= mProgressDialog.getProgress();
                mProgressDialog.setProgress(--progress);
                dialog.dismiss();
            }
        });*/

        return mProgressDialog;
    }

    /**
     * @param activity
     * @param alertDialogThemeId
     * @param mesaj
     * @param isCancelable
     * @return
     */
    public ProgressDialog showLoadingDialogBox(Activity activity, int alertDialogThemeId, String mesaj, boolean isCancelable) {
        ProgressDialog pDialog = new ProgressDialog(activity, alertDialogThemeId);
        pDialog.setMessage(mesaj);
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(isCancelable);
        return pDialog;
    }

    /**
     * @param activity
     * @param mesaj
     * @param isCancelable
     * @return
     */
    public ProgressDialog showLoadingDialogBox(Activity activity, String mesaj, boolean isCancelable) {
        ProgressDialog pDialog = new ProgressDialog(activity);
        pDialog.setMessage(mesaj);
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(isCancelable);
        return pDialog;
    }

    /**
     * @param activity
     * @param alertDialogThemeId
     * @param baslik
     * @param isCancelable
     * @param layoutResID
     * @return
     */
    public Dialog showCustomDialogContentViewBox(Activity activity, int alertDialogThemeId, String baslik, boolean isCancelable, int layoutResID) {
        Dialog dialog = GetirDialog(activity, alertDialogThemeId);
        dialog.setTitle(baslik);
        dialog.setCancelable(isCancelable);
        dialog.setContentView(layoutResID);

        return dialog;
    }

    /**
     * @param activity
     * @param alertDialogThemeId
     * @param baslik
     * @param isCancelable
     * @param iconAttrId
     * @param layoutResID
     * @param isPositiveButton
     * @param positiveButtonText
     * @param positiveButtonListener
     * @param isNegativeButton
     * @param negativeButtonText
     * @param negativeButtonListener
     * @param isNeutralButton
     * @param neutralButtonText
     * @param neutralButtonListener
     * @return
     */
    public AlertDialog showCustomDialogViewBox(Activity activity, int alertDialogThemeId, String baslik, boolean isCancelable, int iconAttrId, int layoutResID,
                                               boolean isPositiveButton, String positiveButtonText, DialogInterface.OnClickListener positiveButtonListener,
                                               boolean isNegativeButton, String negativeButtonText, DialogInterface.OnClickListener negativeButtonListener,
                                               boolean isNeutralButton, String neutralButtonText, DialogInterface.OnClickListener neutralButtonListener) {
        LayoutInflater factory = LayoutInflater.from(activity);
        final View textEntryView = factory.inflate(layoutResID, null);

        AlertDialog.Builder builder = GetirAlertBuilder(activity, alertDialogThemeId);
        builder.setTitle(baslik);
        builder.setCancelable(isCancelable);
        builder.setIconAttribute(iconAttrId);
        builder.setView(textEntryView);

        if (isPositiveButton)
            builder.setPositiveButton(positiveButtonText, positiveButtonListener);
        if (isNegativeButton)
            builder.setNegativeButton(negativeButtonText, negativeButtonListener);
        if (isNeutralButton)
            builder.setNeutralButton(neutralButtonText, neutralButtonListener);

        return builder.create();
    }

    /**
     * @param activity
     * @param alertDialogThemeId
     * @param baslik
     * @param isCancelable
     * @param iconAttrId
     * @param layoutResID
     * @param isPositiveButton
     * @param positiveButtonTextId
     * @param positiveButtonListener
     * @param isNegativeButton
     * @param negativeButtonTextId
     * @param negativeButtonListener
     * @param isNeutralButton
     * @param neutralButtonTextId
     * @param neutralButtonListener
     * @return
     */
    public AlertDialog showCustomDialogViewBox(Activity activity, int alertDialogThemeId, String baslik, boolean isCancelable, int iconAttrId, int layoutResID,
                                               boolean isPositiveButton, int positiveButtonTextId, DialogInterface.OnClickListener positiveButtonListener,
                                               boolean isNegativeButton, int negativeButtonTextId, DialogInterface.OnClickListener negativeButtonListener,
                                               boolean isNeutralButton, int neutralButtonTextId, DialogInterface.OnClickListener neutralButtonListener) {
        LayoutInflater factory = LayoutInflater.from(activity);
        final View textEntryView = factory.inflate(layoutResID, null);

        AlertDialog.Builder builder = GetirAlertBuilder(activity, alertDialogThemeId);
        builder.setTitle(baslik);
        builder.setCancelable(isCancelable);
        builder.setIconAttribute(iconAttrId);
        builder.setView(textEntryView);

        if (isPositiveButton)
            builder.setPositiveButton(positiveButtonTextId, positiveButtonListener);
        if (isNegativeButton)
            builder.setNegativeButton(negativeButtonTextId, negativeButtonListener);
        if (isNeutralButton)
            builder.setNeutralButton(neutralButtonTextId, neutralButtonListener);

        return builder.create();
    }

  /*  http://www.vogella.com/code/ApiDemos/src/com/example/android/apis/app/AlertDialogSamples.html

  public  void ddd(Activity activity){
  case DIALOG_MULTIPLE_CHOICE_CURSOR:
                String[] projection = new String[] {
                        ContactsContract.Contacts._ID,
                        ContactsContract.Contacts.DISPLAY_NAME,
                        ContactsContract.Contacts.SEND_TO_VOICEMAIL
                };
                Cursor cursor = managedQuery(ContactsContract.Contacts.CONTENT_URI,
                        projection, null, null, null);
                return new AlertDialog.Builder(AlertDialogSamples.this)
                    .setIcon(R.drawable.ic_popup_reminder)
                    .setTitle(R.string.alert_dialog_multi_choice_cursor)
                    .setMultiChoiceItems(cursor,
                            ContactsContract.Contacts.SEND_TO_VOICEMAIL,
                            ContactsContract.Contacts.DISPLAY_NAME,
                            new DialogInterface.OnMultiChoiceClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton,
                                        boolean isChecked) {
                                    Toast.makeText(AlertDialogSamples.this,
                                            "Readonly Demo Only - Data will not be updated",
                                            Toast.LENGTH_SHORT).show();
                                }
                            })
                   .create();
    }*/

    /**
     * @param activity
     * @param key
     * @param value
     * @param type
     * @param <T>
     */
    public <T> void ekleSharedPreference(Activity activity, String key, Object value, Class<T> type) {
        SharedPreferences sp = activity.getSharedPreferences("MyApp", Context.MODE_PRIVATE);
        SharedPreferences.Editor spEditor = sp.edit();
        if (String.class.isAssignableFrom(type)) {
            String val = (String) value;
            spEditor.putString(key, val);
        } else if (int.class.isAssignableFrom(type)) {
            int val = (int) value;
            spEditor.putInt(key, val);
        } else if (long.class.isAssignableFrom(type)) {
            long val = (long) value;
            spEditor.putLong(key, val);
        } else if (Boolean.class.isAssignableFrom(type)) {
            Boolean val = (Boolean) value;
            spEditor.putBoolean(key, val);
        } else if (Float.class.isAssignableFrom(type)) {
            Float val = (Float) value;
            spEditor.putFloat(key, val);
        } else {

        }
        /*switch (type){
            case aClass ;:
                String val=(String)value;
                spEditor.putString(key,val);
                break;
        }*/


        spEditor.commit();
    }

    /**
     * ActionBarActivity ekran tiplerinde hata verir
     *
     * @param activity
     */
    public void gizleEkranBasligini(Activity activity) {
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    /**
     * @param dialog
     */
    public void gizleEkranBasligini(Dialog dialog) {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    /**
     * @param activity
     */
    public void tamEkran(Activity activity) {
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * @param dialog
     */
    public void tamEkran(Dialog dialog) {
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * ActionBarActivity ekran tiplerinde hata verir
     *
     * @param activity
     */
    public void tamEkranGizliEkranBasligi(Activity activity) {
        gizleEkranBasligini(activity);
        tamEkran(activity);
    }

    /**
     * @param dialog
     */
    public void tamEkranGizliEkranBasligi(Dialog dialog) {
        gizleEkranBasligini(dialog);
        tamEkran(dialog);
    }
}