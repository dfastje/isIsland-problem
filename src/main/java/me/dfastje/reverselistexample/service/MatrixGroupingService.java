package me.dfastje.reverselistexample.service;

import me.dfastje.reverselistexample.DataVector;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Service
public class MatrixGroupingService {

    //Reference information
    private boolean[][] locationCheckedMatrix;
    private boolean[][] geographicMatrix;
    private DataVector maxSizeVector;

    /**
     * Count the groupings of land in a 2D geographical map
     *
     * @param geographicMatrix
     * @return
     */
    public int countIslands(boolean[][] geographicMatrix){
        //initialize setup
        int maxHeight = geographicMatrix.length;
        int maxLength = geographicMatrix[0].length;
        this.locationCheckedMatrix = new boolean[maxHeight][maxLength];
        this.maxSizeVector = new DataVector(maxLength, maxHeight);
        this.geographicMatrix = geographicMatrix;

        int numIslandsFound = 0;
        for (int y = 0; y < geographicMatrix.length ; y++) {
            for (int x = 0; x < geographicMatrix[0].length; x++) {
                DataVector location = new DataVector(x,y);
                numIslandsFound = numIslandsFound + checkForLand(location);
            }
        }

        return numIslandsFound;
    }

    private int checkForLand(DataVector location){
        int newLandFound = 0;
        boolean hasLocationBeenChecked = locationCheckedMatrix[ location.getY() ][ location.getX() ];
        if(hasLocationBeenChecked){
            newLandFound = 0;
        }else {
            locationCheckedMatrix[ location.getY() ][ location.getX() ] = true;
            boolean hasLand = geographicMatrix[ location.getY() ][ location.getX() ];
            if(hasLand){
                List<DataVector> adjacentLocations = findAdjancetLocations(location);
                adjacentLocations.forEach( adjancentLocation -> checkForLand(adjancentLocation) );
                newLandFound = 1;
            }
        }
        return newLandFound;
    }

    /**
     *
     * @param initialVector
     * @return
     */
    private List<DataVector> findAdjancetLocations(DataVector initialVector){
        List<DataVector> vectorList = new LinkedList<>();
        int x = initialVector.getX();
        int y = initialVector.getY();

        DataVector deceaseX = new DataVector(x-1, y);
        DataVector deceaseY = new DataVector(x  , y+1);
        DataVector increaseX = new DataVector(x+1, y);
        DataVector increaseY = new DataVector(x  , y+1);

        if(isValidLocation( deceaseX )) {
            vectorList.add( deceaseX );
        }
        if(isValidLocation( deceaseY )) {
            vectorList.add( deceaseY );
        }
        if(isValidLocation( increaseX )) {
            vectorList.add( increaseX );
        }
        if(isValidLocation( increaseY )) {
            vectorList.add( increaseY );
        }
        return vectorList;
    }

    private boolean isValidLocation(DataVector location){
        if( 0 > location.getX() ) {
            return false;
        }
        if( 0 > location.getY() ) {
            return false;
        }
        if( maxSizeVector.getX() <= location.getX() ) {
            return false;
        }
        if( maxSizeVector.getY() <= location.getY() ) {
            return false;
        }

        return true;
    }
}
