import { Injectable,Injector } from '@angular/core';
import { HTTP_INTERCEPTORS, HttpInterceptor } from '@angular/common/http';
import { AuthServiceService } from './auth-service.service';


@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor{

  constructor(private authser : AuthServiceService, private injector : Injector) { }
  intercept(req,next){
    let authser = this.injector.get(AuthServiceService);
    let tokenizedreq = req.clone({
      setHeaders :{
        Authorization : `Bearer ${authser.getToken()}`
      }
    });
    return next.handle(tokenizedreq);

  }
}
