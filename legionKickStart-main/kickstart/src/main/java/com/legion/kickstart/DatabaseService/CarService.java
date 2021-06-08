package com.legion.kickstart.DatabaseService;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.legion.kickstart.DatabaseEntities.Car;

import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class CarService {
    private static final String COLLECTION_NAME = "Cars";

    public String saveCar(Car car) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<DocumentReference> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).add(car);
        return "A connector with Id : " + collectionApiFuture.get().getId() + " has been added to the Connectors database";
    }

    public Car getCar(String carId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(carId);
        ApiFuture<DocumentSnapshot> docSnapShot =  documentReference.get();
        DocumentSnapshot document = docSnapShot.get();
        Car requiredCar = null;
        if(document.exists()) {
            requiredCar = document.toObject(Car.class);
        }
        return requiredCar;
    }
    /* --> Pending <--
    public String updateCar(Component component) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<DocumentReference> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).add(component);
        return "A connector with Id : " + collectionApiFuture.get().getId() + " has been added to the Connectors database";
    }
    */

    public String deleteCar(String carId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(carId).delete();
        return "Document with Id " + carId + " has been deleted at : " + collectionApiFuture.get().getUpdateTime().toString();
    }

}
