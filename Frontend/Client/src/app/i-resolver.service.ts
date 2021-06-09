import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { InfosTransfrService } from './infos-transfr.service';
import { Observable } from 'rxjs';
import { take,map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class IResolverService implements Resolve<object> {

  constructor(private infoApi : InfosTransfrService  ) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): object | Observable<object> | Promise<object> {
    let infos:object ={};
    return this.infoApi.getinfos().pipe(
      take(1),
      map(infos => infos)
    )

    throw new Error("Method not implemented.");

  }
}
