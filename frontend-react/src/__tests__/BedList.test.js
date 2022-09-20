import React from "react";
import Enzyme, { shallow, mount } from "enzyme";
import BedList from "../components/BedList";
import Adapter from "enzyme-adapter-react-16";
import * as reactRouterDom from "react-router-dom";
import renderer from "react-test-renderer";
import BedService from "../services/bed.service";
import { render } from "@testing-library/react";

Enzyme.configure({ adapter: new Adapter() });

/**
 * Testing for UI of Login page
 * @author MN097789
 */

const mockNavigate = jest.fn();

jest.mock("react-router-dom", () => ({
  useSelector: jest.fn(),

  useNavigate: () => mockNavigate,
}));
const mockNavigateSpy = jest.spyOn(reactRouterDom, "useNavigate");

describe("BedList UI Test Suite", () => {
  let wrapper;
  beforeEach(() => {
    wrapper = shallow(<BedList />);
  });

  test("Checks if it renders correctly", () => {
    const tree = renderer.create(<BedList />).toJSON();
    expect(tree).toMatchSnapshot();
  });

  test("should check for divs", () => {
    wrapper = shallow(<BedList />);
    expect(wrapper.length).toEqual(1);
  });

  test("BedService.getBedsByRid is called", () => {
    jest.spyOn(React, "useEffect").mockImplementation((f) => f());
    jest.spyOn(BedService, "getBedsByRid");
    render(<BedList />);
    expect(BedService.getBedsByRid).toHaveBeenCalled();
  });
});
