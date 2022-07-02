import "./App.css";
import Sidebar from "./Sidebar/Sidebar";
import Navbar from "./Navbar/Navbar";
import MediaCategoriesDetails from "./GraphDetails/MediaCategoriesDetails";
import Dashboard from "./Dashboard/Dashboard";
import "bootstrap/dist/css/bootstrap.css";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import PrintMode from "./GraphDetails/PrintModesGraphDetails";
import InkUsageDetails from "./GraphDetails/InkUsageDetails";
import MediaTypesPerMachineDetails from "./GraphDetails/MediaTypesPerMachineDetails";
import Top10Details from "./GraphDetails/Top10Details";

function App() {
  return (
    <div className="App">
      <Navbar />
      <div className="container-fluid">
        <div className="row">
          <Sidebar />
          <Router>
            <Switch>
              <Route
                path="/mediacategories"
                exact
                component={MediaCategoriesDetails}
              />
              <Route
                path="/printmodes"
                exact
                component={PrintMode}
              />
              <Route
                path="/inkusage"
                exact
                component={InkUsageDetails}
              />
              <Route
                path="/top10"
                exact
                component={Top10Details}
              />
              <Route
                path="/mediatypespermachine"
                exact
                component={MediaTypesPerMachineDetails}
              />
              <Route path="/dashboard" exact component={Dashboard} />
              <Route path="" exact component={Dashboard} />
            </Switch>
          </Router>
        </div>
      </div>
    </div>
  );
}

export default App;
