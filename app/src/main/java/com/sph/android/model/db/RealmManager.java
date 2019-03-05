package com.sph.android.model.db;

import com.sph.android.model.db.dao.RecordDao;

import io.realm.Realm;

public class RealmManager {

    private static Realm mRealm;

    public static Realm open() {
        mRealm = Realm.getDefaultInstance();
        return mRealm;
    }

    public static void close() {
        if (mRealm != null) {
            mRealm.close();
        }
    }

    public static RecordDao getRecordDao() {
        checkForOpenRealm();
        return new RecordDao(mRealm);
    }

    private static void checkForOpenRealm() {
        if (mRealm == null || mRealm.isClosed()) {
            throw new IllegalStateException("RealmManager: Realm is closed, call open() method first");
        }
    }
}