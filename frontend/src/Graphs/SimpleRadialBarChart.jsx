import "./GraphStyle.css";
import React, { PureComponent } from "react";

import {
  RadialBarChart,
  RadialBar,
  Legend,
  ResponsiveContainer,
} from "recharts";

export default class SimpleRadialBarChart extends PureComponent {
  constructor(props) {
    super(props);
    this.state = {
      bars: [],
      colors: ["#C0392B", "#E74C3C", "#9B59B6", "#2980B9", "#3498DB", "#1ABC9C", "#16A085", "#27AE60", "#F1C40F",
       "#E67E22", "#D35400", "#C0392B", "#E74C3C", "#9B59B6", "#2980B9", "#3498DB", "#1ABC9C", "#16A085", "#27AE60", "#F1C40F", 
       "#C0392B", "#E74C3C", "#9B59B6", "#2980B9", "#3498DB", "#1ABC9C", "#16A085", "#27AE60", "#F1C40F",
       "#E67E22", "#D35400", "#C0392B", "#E74C3C", "#9B59B6", "#2980B9", "#3498DB", "#1ABC9C", "#16A085", "#27AE60", "#F1C40F",
       "#C0392B", "#E74C3C", "#9B59B6", "#2980B9", "#3498DB", "#1ABC9C", "#16A085", "#27AE60", "#F1C40F",
       "#E67E22", "#D35400", "#C0392B", "#E74C3C", "#9B59B6", "#2980B9", "#3498DB", "#1ABC9C", "#16A085", "#27AE60", "#F1C40F",
       "#C0392B", "#E74C3C", "#9B59B6", "#2980B9", "#3498DB", "#1ABC9C", "#16A085", "#27AE60", "#F1C40F",
       "#E67E22", "#D35400", "#C0392B", "#E74C3C", "#9B59B6", "#2980B9", "#3498DB", "#1ABC9C", "#16A085", "#27AE60", "#F1C40F"],
    };
    const style = {
      top: "50%",
      right: 0,
      transform: "translate(0, -50%)",
      lineHeight: "24px",
    };
  }

  componentDidMount() {
    this.tryTest();
  }

  tryTest() {
    var first = this.props.data[0];
    var bars = [];
    var barsValues = [];
    for (const key in first) {
      if (key != "name") {
        bars.push(key);
        barsValues.push(first[key]);
      }
    }
    this.setState({ bars: bars, barsValues: barsValues });
  }

  render() {
    return (
      <div style={{ width: "100%", height: 300 }}>
        <ResponsiveContainer width="100%" height="100%">
          <RadialBarChart
            cx="50%"
            cy="50%"
            innerRadius="10%"
            outerRadius="80%"
            barSize={10}
            data={this.props.data}
          >
            <RadialBar
              minAngle={15}
              label={{ position: "insideStart", fill: "#fff" }}
              background
              clockWise
              dataKey="canvas"
            />
            <Legend
              iconSize={10}
              layout="vertical"
              verticalAlign="middle"
              wrapperStyle={this.style}
            />
          </RadialBarChart>
        </ResponsiveContainer>
      </div>
    );
  }
}
