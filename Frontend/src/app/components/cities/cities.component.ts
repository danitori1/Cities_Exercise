import { Component, OnInit } from '@angular/core';
import { MDCDataTable } from '@material/data-table';
import { MDCSelect } from '@material/select';
import { MDCFloatingLabel } from '@material/floating-label';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-cities',
  templateUrl: './cities.component.html',
  styleUrls: ['./cities.component.css'],
})
export class CitiesComponent implements OnInit {
  tableData: object;
  options = {
    titles: ['ID', 'Name'],
    pageSizes: [5, 10, 25, 40],
  };
  pageSize: number = 5;
  pageNumber: number = 1;
  sortInfo = {
    column: null,
    order: null,
  };
  constructor(private http: HttpClient) {}

  getData() {
    // Call the data API
    var sortParams: string = '';
    if (this.sortInfo.column && this.sortInfo.order) {
      sortParams = `&field=${this.sortInfo.column}&sort=${this.sortInfo.order}`;
    }
    const host: string = `http://192.168.99.100:1111/cities/queryByPage?page=${
      this.pageNumber - 1
    }&size=${this.pageSize}${sortParams}`;
    const local_host: string = `http://localhost:8080/api/cities/queryByPage?page=${
      this.pageNumber - 1
    }&size=${this.pageSize}${sortParams}`;
    this.http.get(local_host).subscribe((res) => {
      this.tableData = res;
      console.log(this.tableData);
    });
    return;
  }

  handleSort(column) {
    // Functionwhen click on th to change the column and sort direction
    if (column !== this.sortInfo['column']) {
      // If column is clicked on the first time always sort on ascent direction.
      this.sortInfo = {
        column: column,
        order: 'ascent',
      };
    } else if (
      column === this.sortInfo['column'] &&
      this.sortInfo['order'] === 'ascent'
    ) {
      // If column clicked is sorted in ascent then change to descent
      this.sortInfo = {
        column: column,
        order: 'descent',
      };
    } else if (
      column === this.sortInfo['column'] &&
      this.sortInfo['order'] === 'descent'
    ) {
      // If column clicked is sorted in descent then change to no sorted
      this.sortInfo = {
        column: null,
        order: null,
      };
    }
    this.getData();
    return;
  }

  handlePage(next) {
    // Modify the page number and reload the table data
    if (next && this.pageNumber !== this.tableData['totalPages']) {
      this.pageNumber += 1;
    } else if (this.pageNumber > 1) {
      this.pageNumber -= 1;
    }
    this.getData();
    return;
  }

  handlePageSize(value) {
    // Modify the page size and reload the table data
    this.pageSize = value;
    this.getData();
  }

  ngOnInit(): void {
    const dataTable = new MDCDataTable(
      document.querySelector('.mdc-data-table')
    );
    const select = new MDCSelect(document.querySelector('.mdc-select'));
    const floatingLabel = new MDCFloatingLabel(
      document.querySelector('.mdc-floating-label')
    );
    this.getData();
  }
}
