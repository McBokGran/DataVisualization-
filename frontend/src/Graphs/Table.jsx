import "./GraphStyle.css";
import React, { PureComponent } from "react";

export default class Table extends PureComponent {
  constructor(props) {
    super(props);
    this.state = {
    };
  }

  componentDidMount() {
  }

  render() {
    return (
      <div className="table-responsive">
        <table className="table table-striped table-hover table-sm">
          <thead>
            <tr>
              <th scope="col">Date</th>
              {this.props.labels.map((b) => (
                <th scope="col">{b}</th>
              ))}
            </tr>
          </thead>
          <tbody>
            {this.props.data.map((d) => (
              <tr>
                <td>{new Date(d.name).toISOString("en-NL").split('T')[0]}</td>
                {this.props.labels.map((b) => (
                  <td>{d[b]}</td>
              ))}
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    );
  }
}
