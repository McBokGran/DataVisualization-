import React, { Component } from "react";
import "./graphdetails.css";
import StackedBarChart from "../Graphs/StackedBarChart.jsx";
import SimpleLineChart from "../Graphs/SimpleLineChart";
import PercentAreaChart from "../Graphs/PercentAreaChart";
import Table from "../Graphs/Table.jsx";
import randomColor from "randomcolor";
import ImageService from "../Api/ImageService";
import DataParser from "../Api/DataParser";
import DateRange from "../DateRangeHelper/DateRangeGenerator"

class MediaCategoriesDetails extends Component {
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
      images: [],
      converted: [],
      labels: [],
      //filtering
      startDate: firstday,
      endDate: lastday,
      filterRange: ""
    };
  }

  async componentDidMount() {
    //window.location.reload(false);
    document.title = "Media Categories";
    var dates = await DateRange.getCurrentWeek();
    var firstday = dates[0];
    var lastday = dates[1];
    console.log(firstday + ", " + lastday);
    await this.getLast12Months();
    
  }

  async getData(startDate, endDate) {
    console.log("startDate "+ startDate, "endDate " + endDate);
    await this.getImages();
    let converted = await DataParser.convertImageData(this.state.images);
    let labels = await ImageService.getMediaTypesPaginated(
      startDate,
      endDate
    ).then(({ data }) => data);
    this.setState({ converted: converted, labels: labels }, () => {console.log(this.state.converted, this.state.labels)});
    console.log(converted);
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
      }else if (this.state.filterRange === "3") {
        this.getLast4Months();
      }else if (this.state.filterRange === "4") {
        this.getLast12Months();
      }
    });

  }

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

  getLast12Months = async () => {
    var [firstday, lastday] = await DateRange.getLast12Months();
    this.setState({ startDate: firstday, endDate: lastday },
      () => this.getData(this.state.startDate, this.state.endDate));
  }

  //sets currently selected chart
  handleListChange(e) {
    this.setState({ selectedChart: e.target.value }, () => {});
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

  render() {
    const { converted, labels } = this.state;
    return (
      <main className="col-md-9 ms-sm-auto col-lg-10 px-md-4">
        <div className="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
          <h1 className="h2">Media Categories</h1>
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
            <div className="">
              <select
                value={this.state.selectedChart}
                onChange={this.handleListChange.bind(this)}
                className="form-select btn-outline-secondary"
                id="exampleFormControlSelect1"
              >
                <option value="">Select a chart</option>
                <option value="stackedbarchart">Stacked Bar Chart</option>
                <option value="simplelinechart">Simple Line Chart</option>
                <option value="percentareachart">Percent Area Chart</option>
              </select>
            </div>
          </div>
        </div>

        {(converted.length > 0) ? (<div>
        {(this.state.selectedChart === "stackedbarchart" ||
          this.state.selectedChart === "") && (
            <div>
              <div width="100%">
                <StackedBarChart
                  data={converted}
                  labels={labels}
                  colors={this.assignColors()}
                  xlabel="Time period"
                  ylabel="Sqm per media type"
                />
              </div>
            </div>
          )}

        {this.state.selectedChart === "simplelinechart" && (
          <div>
            <div width="100%">
              <SimpleLineChart data={converted}
                labels={labels}
                colors={this.assignColors()}
                xlabel="Time period"
                ylabel="Sqm per media type" />
            </div>
          </div>
        )}

        {this.state.selectedChart === "percentareachart" && (
          <div>
            <div width="100%">
              <PercentAreaChart 
                data={converted}
                labels={labels}
                colors={this.assignColors()}
                xlabel="Time period"
                ylabel="Sqm per media type" />
            </div>
          </div>
        )}

        {/* <h4>Test</h4>
        <div width="100%">
          <Test data={testData} colors={this.assignColors()} />
        </div> */}

        {/* Radial not good for displaying large amount of data */}
        {/* <h4>Simple Radial BarChart</h4>
        <div width="100%">
          <SimpleRadialBarChart data={testData} colors={this.assignColors()} />
        </div> */}

        <h4>Data</h4>
        <Table data={converted} labels={labels} />
        </div>) : (<div className="error"><h5>No data available for the selected date range.</h5></div>)}
      </main>
    );
  }
}

export default MediaCategoriesDetails;
