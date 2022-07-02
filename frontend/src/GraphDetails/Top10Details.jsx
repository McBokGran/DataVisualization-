import React, { Component } from "react";
import "./graphdetails.css";
import BarChart from "../Graphs/BarChart.jsx";
import SimpleLineChart from "../Graphs/SimpleLineChart";
import PercentAreaChart from "../Graphs/PercentAreaChart";
import Table from "../Graphs/Table.jsx";
import randomColor from "randomcolor";
import ImageService from "../Api/ImageService";
import DataParser from "../Api/DataParser";
import DateRange from "../DateRangeHelper/DateRangeGenerator"
import top10data from "../top10data.json"
import PrintCycleService from "../Api/PrintCycleService";

class Top10Details extends Component {
  constructor(props) {
    super(props);
    var date = new Date;
    var firstday = new Date(date.getFullYear(), date.getMonth(), 1).toISOString("en-NL").split('T')[0];
    var lastday = new Date(date.getFullYear(), date.getMonth() + 1, 0).toISOString("en-NL").split('T')[0];
    console.log("firstday " + firstday);
    console.log("lastday " + lastday);
    this.state = {
      selectedChart: "",
      hues: ["red", "orange", "purple", "pink", "green", "blue", "yellow"],
      inkusage: [],
      converted: [],
      labels: ["700", "800", "901", "234", "aqret", "qqqq1", "0yyy"],
      //filtering
      startDate: firstday,
      endDate: lastday,
      filterRange: ""
    };
  }

  async componentDidMount() {
    console.log(this.state.converted);
    document.title = "Top 10 Machines";
    
    await this.getLast12Months();
  }

  async getData(startDate, endDate) {
    console.log(startDate, endDate);
    await this.getTopten();
    let converted = await DataParser.convertTopTenData(this.state.inkusage);

    let labels = await PrintCycleService.getMachineIdsPaginated(
      startDate,
      endDate
    ).then(({ data }) => data);
    this.setState({ converted: converted, labels: labels});
    console.log(converted);
  }

  assignColors() {
    const hue =
      this.state.hues[Math.floor(Math.random() * this.state.hues.length)];
    return randomColor({
      count: 50,
      luminosity: "bright",
      hue: hue,
    });
  }

  //sets currently selected chart
  handleListChange(e) {
    this.setState({ selectedChart: e.target.value }, () => {
      console.log("this.state.selectedChart " + this.state.selectedChart);
      console.log(this.state.selectedChart == "stackedbarchart");
    });
  }

  handleFilterChange = async (e) => {
    this.setState({ filterRange: e.target.value }, () => {
      if (this.state.filterRange === "0") {
        this.getCurrentWeek();
      } else if (this.state.filterRange === "1") {
        this.getCurrentMonth();
      } else if (this.state.filterRange === "2") {
        this.getLast3Months();
      }else if (this.state.filterRange === "3") {
        this.getLast4Months();
      }else if (this.state.filterRange === "4") {
        this.getLast12Months();
      }
    });

  }

  getCurrentWeek = async () => {
    var [firstday, lastday] = await DateRange.getCurrentWeek();
    this.setState({ startDate: firstday, endDate: lastday },
      () => this.getData(this.state.startDate, this.state.endDate));
  }

  getCurrentMonth = async () => {
    var [firstday, lastday] = await DateRange.getCurrentMonth();
    this.setState({ startDate: firstday, endDate: lastday },
      () => this.getData(this.state.startDate, this.state.endDate));
  }

  getLast3Months = async () => {
    var [firstday, lastday] = await DateRange.getLast3Months();
    this.setState({ startDate: firstday, endDate: lastday },
      () => this.getData(this.state.startDate, this.state.endDate));
  }

  getLast4Months =async () => {
    var [firstday, lastday] = await DateRange.getLast4Months();
    this.setState({startDate: firstday, endDate: lastday}, 
      () => this.getData(this.state.startDate, this.state.endDate));
  }

  getLast12Months = async () => {
    var [firstday, lastday] = await DateRange.getLast12Months();
    this.setState({ startDate: firstday, endDate: lastday },
      () => this.getData(this.state.startDate, this.state.endDate));
  }

  getTopten = async () => {
    let data = await PrintCycleService.getTopTenMachinesPaginated(
      this.state.startDate,
      this.state.endDate
    ).then(({ data }) => data);
    this.setState({
        inkusage: data,
    });
  };

  render() {
    const { converted, labels } = this.state;
    return (
      <main className="col-md-9 ms-sm-auto col-lg-10 px-md-4">
        <div className="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
          <h1 className="h2">Top 10 Machines</h1>
          <div className="btn-toolbar mb-2 mb-md-0">
            <div className="btn-group me-2">
              <button type="button" className="btn btn-sm btn-outline-secondary" disabled>
                Share
              </button>
              <button type="button" className="btn btn-sm btn-outline-secondary" disabled>
                Export
              </button>
            </div>
            <div className="">
              <select
                className="form-select btn-outline-secondary"
                id="inputGroupSelect04"
                aria-label="Example select with button addon"
                onChange={this.handleFilterChange.bind(this)}
              >
                <option value="" >Select Range</option>
                <option value="0">This week</option>
                <option value="1">This month</option>
                <option value="2">Last 3 months</option>
                <option value="3">Last 120 days</option>
                <option value="4">Last year</option>
              </select>
            </div>
            
          </div>
        </div>

        {(converted.length > 0) ? (<div>{(this.state.selectedChart === "stackedbarchart" ||
          this.state.selectedChart === "") && (
            <div>
              <div width="100%">
                <BarChart
                  data={converted}
                  labels={labels}
                  colors={this.assignColors()}
                  xlabel="Printer"
                  ylabel="Print Volume"
                />
              </div>
            </div>
          )}
          </div>) : (<div className="error"><h5>No data available for the selected date range.</h5></div>)}

      </main>
    );
  }
}

export default Top10Details;
