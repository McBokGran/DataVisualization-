import React, { Component } from "react";
import randomColor from "randomcolor";
import ImageService from "../Api/ImageService";
import DataParser from "../Api/DataParser";
import StackedBarChart from "../Graphs/StackedBarChart.jsx";
import SimpleLineChart from "../Graphs/SimpleLineChart";
import PercentAreaChart from "../Graphs/PercentAreaChart";
import PieGraph from "../Graphs/PieGraph";
import "./dashboard.css";
import DateRange from "../DateRangeHelper/DateRangeGenerator";
import PrintCycleService from "../Api/PrintCycleService";

class Dashboard extends Component {
  constructor(props) {
    super(props);
    var date = new Date;
    var firstday = new Date(date.getFullYear(), date.getMonth(), 1).toISOString("en-NL").split('T')[0];
    var lastday = new Date(date.getFullYear(), date.getMonth() + 1, 0).toISOString("en-NL").split('T')[0];
    console.log("firstday " + firstday, "lastday " + lastday);
    this.state = {
      selectedChart: "",
      hues: ["red", "orange", "purple", "pink", "green", "blue", "yellow"],
      images: [],
      printcycles: [],
      converted: [],
      labels: [],
      convertedPrint: [],
      labelsPrint: [],
      convertedInk: [],
      labelsInk: [],
      //pagination
      startDate: firstday,
      endDate: lastday
    };
  }

  async componentDidMount() {
    //window.location.reload(false);
    document.title = "Dashboard";
    await this.getLast12Months();
  }

  async getData(startDate, endDate) {
    console.log("startDate "+ startDate, "endDate " + endDate);
    await this.getImages();
    await this.getPrintCycles();
    await this.getInkUsage();
    let converted = await DataParser.convertImageData(this.state.images);
    // console.log(converted);
    // console.log(Object.keys(converted[0])[3]);
    let labels = await ImageService.getMediaTypesPaginated(
      startDate,
      endDate
    ).then(({ data }) => data);
    let convertedPrint = await DataParser.convertPrintCycleData(this.state.printcycles);
    let labelsPrint = await PrintCycleService.getPrintModesPaginated(
      startDate,
      endDate
    ).then(({ data }) => data);
    let convertedInk = await DataParser.convertInkUsageData(this.state.inkusage);
    let labelsInk = await ImageService.getInkTypesPaginated(
      startDate,
      endDate
    ).then(({ data }) => data);
    this.setState({ converted: converted, labels: labels, convertedPrint: convertedPrint,
       labelsPrint: labelsPrint, convertedInk: convertedInk, labelsInk: labelsInk});
  }

  // TODO put in different folder for reusability
  getCurrentWeek = async () => {
    var [firstday, lastday] = await DateRange.getCurrentWeek();
    this.setState({startDate: firstday, endDate: lastday}, 
      () => this.getData(this.state.startDate, this.state.endDate));
  }

  getCurrentMonth = async () => {
    var [firstday, lastday] = await DateRange.getCurrentMonth();
    this.setState({startDate: firstday, endDate: lastday}, 
      () => this.getData(this.state.startDate, this.state.endDate));
  }

  getLast3Months =async () => {
    var [firstday, lastday] = await DateRange.getLast3Months();
    this.setState({startDate: firstday, endDate: lastday}, 
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
      () => this.getData(this.state.startDate, this.state.endDate), ()=> console.log("startDateeeee " + this.state.startDate, "endDateee " + this.state.endDate));
  }

  assignColors() {
    const hue =
      this.state.hues[Math.floor(Math.random() * this.state.hues.length)];
    return randomColor({
      count: 10,
      luminosity: "bright",
      hue: hue,
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
      } else if (this.state.filterRange === "3") {
        this.getLast4Months();
      } else if (this.state.filterRange === "4") {
        this.getLast12Months();
      }
      console.log("this.state.filterRange " + this.state.filterRange);
      console.log(this.state.filterRange === "1");
    });

  }

  //sets currently selected chart
  handleListChange(e) {
    this.setState({ selectedChart: e.target.value }, () => {
      console.log("this.state.selectedChart " + this.state.selectedChart);
      console.log(this.state.selectedChart === "stackedbarchart");
    });
  }

  getImages = async () => {
    let data = await ImageService.getImagesPaginated(
      this.state.startDate,
      this.state.endDate
    ).then(({ data }) => data, console.log("startDateee " + this.state.startDate, "endDateee " + this.state.endDate));
    this.setState({
      images: data,
    });
  };

  getPrintCycles = async () => {
    let data = await PrintCycleService.getPrintCyclesPaginated(
      this.state.startDate,
      this.state.endDate
    ).then(({ data }) => data);
    this.setState({
      printcycles: data,
    });
  };

  getInkUsage = async () => {
    let data = await ImageService.getInkUsagePaginated(
      this.state.startDate,
      this.state.endDate
    ).then(({ data }) => data);
    this.setState({
        inkusage: data,
    });
  };

  render() {
    const { converted, labels, convertedPrint, labelsPrint, convertedInk, labelsInk } = this.state;
    return (
      <main className="dashboard col-md-9 ms-sm-auto col-lg-10 px-md-4">
        <div className="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
          <h1 className="h2">Dashboard</h1>
          <div className="btn-toolbar mb-2 mb-md-0">
            <div className="btn-group me-2">
              <button type="button" className="btn btn-sm btn-outline-secondary">
                Share
              </button>
              <button type="button" className="btn btn-sm btn-outline-secondary">
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
  
        {/* <GraphRow /> */}
       
        <div width="100%">
          <div className="row">
            <div className="two col">
            <h4>Media Categories</h4>
              <StackedBarChart 
                data={converted}
                labels={labels}
                colors={this.assignColors()}
                xlabel="Time period"
                ylabel="Sqm per media type" 
              />
            </div>
            <div className="two col">
            <h4>Print Modes</h4>
            <StackedBarChart 
                data={convertedPrint}
                labels={labelsPrint}
                colors={this.assignColors()}
                xlabel="Time period"
                ylabel="Sqm per print mode" 
              />
            </div>
          </div>
        </div>

        <br></br>
        <div className="one" width="100%">
          <h4>Media Categories</h4>
          <PercentAreaChart data={converted}
            labels={labels}
            colors={this.assignColors()}
            xlabel="Time period"
            ylabel="Sqm per media type" />
        </div>

        <div width="100%">
          <div className="row">
            <div className="two col">
            <h4>Ink Usage</h4>
              <StackedBarChart 
                data={convertedInk}
                labels={labelsInk}
                colors={this.assignColors()}
                xlabel="Time period"
                ylabel="Liter per ink type" 
              />
            </div>
            <div className="two col">
            <h4>Ink Usage</h4>
            <PercentAreaChart 
                data={convertedPrint}
                labels={labelsPrint}
                colors={this.assignColors()}
                xlabel="Time period"
                ylabel="Sqm per print mode" 
              />
            </div>
          </div>
        </div>

        <br></br>
        <div width="100%">
          <div className="row">
            <div className="two col">
            <h4>Sqm paper per date</h4>
            <PieGraph data={converted}
              labels={labels}
              colors={this.assignColors()}
              xlabel="Time period"
              ylabel="Sqm per media type"
              value="Paper"
            />
            </div>
            
            <div className="two col">
            <h4>Sqm Canvas per date</h4>
            <PieGraph data={converted}
              labels={labels}
              colors={this.assignColors()}
              xlabel="Time period"
              ylabel="Sqm per media type"
              value="Canvas"
            />
            </div>
          </div>
        </div>
      </main>
    );
  }
  
};

export default Dashboard;
