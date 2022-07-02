import "./GraphStyle.css";
import React, { PureComponent } from "react";
import {
  AreaChart,
  Area,
  XAxis,
  YAxis,
  Label,
  CartesianGrid,
  Tooltip,
  ResponsiveContainer,
  Legend,
} from "recharts";

export default class SimpleLineChart extends PureComponent {
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
    const toPercent = (decimal, fixed = 0) =>
      `${(decimal * 100).toFixed(fixed)}%`;

    const getPercent = (value, total) => {
      const ratio = total > 0 ? value / total : 0;
      console.log(value + ", " + total)

      return toPercent(ratio, 2);
    };

    const renderTooltipContent = (o) => {
      const { payload, label } = o;
      const total = payload.reduce((result, entry) => result + entry.value, 0);

      return (
        <div className="customized-tooltip-content">
          <p className="total">{`${label} (Total: ${total})`}</p>
          <ul className="list">
            {payload.map((entry, index) => (
              <li key={`item-${index}`} style={{ color: entry.color }}>
                {`${entry.name}: ${entry.value}(${getPercent(
                  entry.value,
                  total
                )})`}
              </li>
            ))}
          </ul>
        </div>
      );
    };
  }

  componentDidMount() {}

  render() {
    return (
      <div style={{ width: "100%", height: 300 }}>
        <ResponsiveContainer width="100%" height="100%">
          <AreaChart
            width={500}
            height={300}
            data={this.props.data}
            stackOffset="expand"
            margin={{
              top: 5,
              right: 30,
              left: 20,
              bottom: 5,
            }}
          >
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis dataKey="name" ><Label
                angle="0"
                value={this.props.xlabel}
                position="bottom"
                offset={20}
                style={{ textAnchor: "middle" }}
              /></XAxis>
            <YAxis tickFormatter={this.toPercent}> <Label
                angle="270"
                value={this.props.ylabel}
                position="left"
                style={{ textAnchor: "middle" }}
              /></YAxis>
            <Tooltip content={this.renderTooltipContent} />
            <Legend
              verticalAlign="bottom"
              height={36}
              iconType="circle"
              margin={36}
            />
            {this.props.labels.map((label, index) => (
              <Area
                type="monotone"
                key={index}
                dataKey={label}
                stackId="1"
                stroke={this.state.colors[index]}
                fill={this.state.colors[index]}
              />
            ))}
          </AreaChart>
        </ResponsiveContainer>
      </div>
    );
  }
}
