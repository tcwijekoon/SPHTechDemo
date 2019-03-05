package com.sph.android.model.db.dao;

import android.support.annotation.NonNull;

import com.sph.android.model.db.data.RecordDb;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RecordDao {

    private Realm mRealm;

    public RecordDao(@NonNull Realm realm) {
        mRealm = realm;
    }

    public void save(final RecordDb record) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(record);
            }
        });
    }

    public void save(final List<RecordDb> recordList) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(recordList);
            }
        });
    }

    public RealmResults<RecordDb> loadAllRecords() {
        return mRealm.where(RecordDb.class).findAll();
    }

    public long count() {
        return mRealm.where(RecordDb.class).count();
    }
}
