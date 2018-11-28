import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { CalculationRequest } from './calculationrequest.type';
import { assertNumber } from '@angular/core/src/render3/assert';
import { HttpService } from './service/http.service';
import { isNullOrUndefined } from 'util';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'CCSP - Docker Calculator';

  firstOperand: FormControl;
  secondOperand: FormControl;
  operator: FormControl;

  calculationResult: number = null;
  calculationError: string = null;

  constructor(
    private readonly fb: FormBuilder,
    private httpService: HttpService
  ) { }

  ngOnInit(): void {
    this.firstOperand = this.fb.control({});
    this.firstOperand.setValue("");
    this.secondOperand = this.fb.control({});
    this.secondOperand.setValue("");
    this.operator = this.fb.control({});
    this.operator.setValue("ADD");
  }

  performCalculation() {
    this.calculationResult = null;
    this.calculationError = null;

    const operands = Array<string>();
    operands.push(this.firstOperand.value as string);
    operands.push(this.secondOperand.value as string);

    const requestBody: CalculationRequest = {
      operands: operands,
      operator: this.operator.value as string
    };

    this.httpService.requestCalculationResultFor(requestBody).subscribe(response => {
      this.calculationResult = response.body as number;
    }, error => {
      if (error.status === 400) {
        this.calculationError = error.error;
      }
    });
  }

  isButtonDisabled(): boolean {
    return isNullOrUndefined(this.firstOperand.value) || isNullOrUndefined(this.secondOperand.value);
  }

  isCalulationResultVisible(): boolean {
    return this.calculationResult != null;
  }

  isCalulationErrorVisible(): boolean {
    return this.calculationError != null;
  }
}
