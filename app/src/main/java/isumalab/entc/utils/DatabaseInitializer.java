package isumalab.entc.utils;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import isumalab.entc.entity.ModuleEntity;

import static isumalab.entc.utils.DatabaseInitializer.PopulateDbAsync.populateWithTestData;

public class DatabaseInitializer {
    private static final int DELAY_MILLIS = 500;

    public static void populateAsync(final AppDatabase db) {

        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }

    public static void populateSync(@NonNull final AppDatabase db) {
        populateWithTestData(db);
    }

    public static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDatabase mDb;

        PopulateDbAsync(AppDatabase db) {
            mDb = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            populateWithTestData(mDb);
            return null;
        }

        public static void populateWithTestData(AppDatabase db) {
            db.moduleModel().deleteAll();

            ModuleEntity moduleEntity = new ModuleEntity();

            moduleEntity.setId(1);
            moduleEntity.setModule_name("Electronics");
            moduleEntity.setModule_code("EN0001");
            moduleEntity.setSemester_no(5);
            moduleEntity.setActive(true);
            moduleEntity.setCredit(4);
            moduleEntity.setGpa(true);

            db.moduleModel().insertModuleEntity(moduleEntity);

        }

    }

}
