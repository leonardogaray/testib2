import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class CommonsHelper {

    sortAssocArray(assocArray: any[]): any[] {
        var tuples = [];

        for (var key in assocArray) tuples.push([key, assocArray[key]]);

        tuples.sort(function (a, b) {
            a = a[1];
            b = b[1];
            return a < b ? 1 : (a > b ? -1 : 0);
        });

        return tuples;
    }
}