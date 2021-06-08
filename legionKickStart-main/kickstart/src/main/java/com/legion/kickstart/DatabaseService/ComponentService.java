package com.legion.kickstart.DatabaseService;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.legion.kickstart.DatabaseEntities.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class ComponentService {
    private static final String COLLECTION_NAME = "Components";

    public String saveComponent(Component component) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<DocumentReference> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).add(component);
        return "A connector with Id : " + collectionApiFuture.get().getId() + " has been added to the Connectors database";
    }

    public Component getComponent(String componentId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(componentId);
        ApiFuture<DocumentSnapshot> docSnapShot =  documentReference.get();
        DocumentSnapshot document = docSnapShot.get();
        Component requiredComponent = null;
        if(document.exists()) {
            requiredComponent = document.toObject(Component.class);
        }
        return requiredComponent;
    }
    /* --> Pending <--
    public String updateComponent(Component component) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<DocumentReference> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).add(component);
        return "A connector with Id : " + collectionApiFuture.get().getId() + " has been added to the Connectors database";
    }
    */

    public String deleteComponent(String componentId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(componentId).delete();
        return "Document with Id " + componentId + " has been deleted at : " + collectionApiFuture.get().getUpdateTime().toString();
    }
}
