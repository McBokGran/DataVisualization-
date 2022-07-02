import React, { PureComponent } from "react";

import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
  Label,
  ResponsiveContainer,
} from "recharts";

export default class StackedBarChart extends PureComponent {
  constructor(props) {
    super(props);
    this.state = {
      colors: ["#C0392B", "#E74C3C", "#9B59B6", "#2980B9", "#3498DB", "#1ABC9C", "#16A085", "#27AE60", "#F1C40F",
       "#E67E22", "#D35400", "#C0392B", "#E74C3C", "#9B59B6", "#2980B9", "#3498DB", "#1ABC9C", "#16A085", "#27AE60", "#F1C40F", 
       "#C0392B", "#E74C3C", "#9B59B6", "#2980B9", "#3498DB", "#1ABC9C", "#16A085", "#27AE60", "#F1C40F",
       "#E67E22", "#D35400", "#C0392B", "#E74C3C", "#9B59B6", "#2980B9", "#3498DB", "#1ABC9C", "#16A085", "#27AE60", "#F1C40F",
       "#C0392B", "#E74C3C", "#9B59B6", "#2980B9", "#3498DB", "#1ABC9C", "#16A085", "#27AE60", "#F1C40F",
       "#E67E22", "#D35400", "#C0392B", "#E74C3C", "#9B59B6", "#2980B9", "#3498DB", "#1ABC9C", "#16A085", "#27AE60", "#F1C40F",
       "#C0392B", "#E74C3C", "#9B59B6", "#2980B9", "#3498DB", "#1ABC9C", "#16A085", "#27AE60", "#F1C40F",
       "#E67E22", "#D35400", "#C0392B", "#E74C3C", "#9B59B6", "#2980B9", "#3498DB", "#1ABC9C", "#16A085", "#27AE60", "#F1C40F"],
    };
  }

  componentDidMount() {}

  render() {
    return (
      <div style={{ width: "100%", height: 300 }}>
        <ResponsiveContainer>
          <BarChart
            width={500}
            height={300}
            data={this.props.data}
            margin={{
              top: 5,
              right: 30,
              left: 20,
              bottom: 5,
            }}
          >
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis dataKey="name">
              <Label
                angle="0"
                value={this.props.xlabel}
                position="bottom"
                offset={20}
                style={{ textAnchor: "middle" }}
              />
            </XAxis>
            <YAxis>
              <Label
                angle="270"
                value={this.props.ylabel}
                position="left"
                style={{ textAnchor: "middle" }}
              />
            </YAxis>

            <Tooltip />
            <Legend
              verticalAlign="bottom"
              height={36}
              iconType="circle"
              margin={36}
            />
            {this.props.labels.map((label, index) => (
              <Bar
                key={index}
                dataKey={label}
                fill={this.state.colors[index]}
                stackId="stack"
              />
            ))}
          </BarChart>
        </ResponsiveContainer>
      </div>
    );
  }
}
