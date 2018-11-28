import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CalculationRequest } from '../calculationrequest.type';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(
    private httpClient: HttpClient
  ) { }

  requestCalculationResultFor(calulationRequest: CalculationRequest) {
    const body = JSON.stringify(calulationRequest);
    const headers = new HttpHeaders().append('Content-Type', 'application/json');
    return this.httpClient.post('/rest/calculate', body, { headers: headers, observe: 'response' });
  }
}
